package com.recipe.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.query.QueryUtils;

import com.recipe.business.dto.ListingDTO;
import com.recipe.business.dto.SearchDTO;
import com.recipe.business.enums.DishType;
import com.recipe.business.enums.SearchType;
import com.recipe.integration.domain.IngredientMaster;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaginateUtil {

	private PaginateUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static <T> Page<T> paginate(EntityManager em, ListingDTO listingDto, Class<T> clazz) {

		log.info("Listing Dto {}", listingDto);

		final String likesymbol = "%";
		Pageable pageable = null;
		Sort sort = null;

		if (CollectionUtils.isNotEmpty(listingDto.getAscendingSortFields())) {
			log.info("Sort columns for entity: {} => {}", clazz.getCanonicalName(),
					listingDto.getAscendingSortFields());
			sort = Sort.by(Sort.Direction.ASC, listingDto.getAscendingSortFields().stream().toArray(String[]::new));

		}

		if (CollectionUtils.isNotEmpty(listingDto.getDescendingSortFields())) {
			log.info("Sort columns for entity: {} => {}", clazz.getCanonicalName(),
					listingDto.getDescendingSortFields());

			if (sort != null) {
				sort.and(Sort.by(Sort.Direction.DESC,
						listingDto.getDescendingSortFields().stream().toArray(String[]::new)));
			} else {
				sort = Sort.by(Sort.Direction.DESC,
						listingDto.getDescendingSortFields().stream().toArray(String[]::new));
			}

		}

		if (sort == null) {
			sort = Sort.by(listingDto.getDefaultSort().getSortOrder(),
					listingDto.getDefaultSort().getSortFields().stream().toArray(String[]::new));
		}

		pageable = PageRequest.of(listingDto.getStart(), listingDto.getLength(), sort);

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(clazz);
		Root<T> root = criteria.from(clazz);
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(FieldName.ACTIVE), listingDto.getStatus()));

		if (CollectionUtils.isNotEmpty(listingDto.getSearch())) {
			log.info("Search columns for entity {} => {}", clazz.getCanonicalName(), listingDto.getSearch());
			for (SearchDTO searchDto : listingDto.getSearch()) {
				performSearch(likesymbol, builder, root, predicates, searchDto);
			}
		}

		Predicate andSearchPredicateVar = builder.and(predicates.toArray(new Predicate[predicates.size()]));

		criteria.where(builder.and(andSearchPredicateVar));
		criteria.orderBy(QueryUtils.toOrders(pageable.getSort(), root, builder));

		TypedQuery<T> typedQuery = em.createQuery(criteria);

		List<T> result = typedQuery.setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize())
				.getResultList();

		return new PageImpl<>(result, pageable, result.size());

	}

	private static <T> void performSearch(final String likesymbol, CriteriaBuilder builder, Root<T> root,
			List<Predicate> predicates, SearchDTO searchDto) {
		Join<T, IngredientMaster> joinTable;
		String decodedSearch = LanguageUtil.decodeValue(searchDto.getSearch());
		String searchCol = searchDto.getSearchCol();
		if (StringUtils.isNotEmpty(searchCol) && StringUtils.isNoneEmpty(decodedSearch)) {

			if (searchDto.getSearchType() == null) {
				searchDto.setSearchType(SearchType.CONTAINS);
			}
			SearchType searchType = searchDto.getSearchType();

			switch (searchCol) {
			case FieldName.DISH_TYPE:
				predicates.add(builder.equal(root.get(FieldName.DISH_TYPE), DishType.valueOf(decodedSearch)));
				break;
			case FieldName.NO_OF_SERVINGS:
				predicates.add(builder.equal(root.get(FieldName.NO_OF_SERVINGS), Long.parseLong(decodedSearch)));
				break;
			case FieldName.INSTRUCTIONS:
				predicates.add(builder.like(builder.lower(root.get(searchDto.getSearchCol())),
						likesymbol.concat(decodedSearch.toLowerCase()).concat(likesymbol)));
				break;
			case FieldName.INGREDIENT_NAME:
				joinTable = root.join(FieldName.INGREDIENTS, JoinType.LEFT);
				predicates.add(builder.equal(joinTable.get(FieldName.ACTIVE), Boolean.TRUE));

				if (SearchType.NOT_CONTAINS.equals(searchType)) {
					predicates.add(builder.notEqual(joinTable.get(FieldName.INGREDIENT_NAME), decodedSearch));
				} else {
					predicates.add(builder.equal(joinTable.get(FieldName.INGREDIENT_NAME), decodedSearch));
				}
				break;

			default:
				log.info("No matching column found for provided Search column: {}", searchCol);
				break;
			}

		}
	}

}
