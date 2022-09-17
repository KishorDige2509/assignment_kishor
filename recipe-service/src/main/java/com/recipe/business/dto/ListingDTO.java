package com.recipe.business.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListingDTO {

	private Integer start = 0;
	private Integer length = 0;

	private SortDto defaultSort;

	private Boolean status = Boolean.TRUE;

	private List<SearchDTO> search;

	private List<String> ascendingSortFields;

	private List<String> descendingSortFields;

}
