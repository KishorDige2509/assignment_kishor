package com.recipe.business.dto;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.recipe.business.enums.SearchType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchDTO {

	private String search;

	private String searchCol;

	private SearchType searchType;

	public String getSearch() {
		if (StringUtils.isBlank(search)) {
			return search;
		} else {
			Pattern arrayBracketPattern = Pattern.compile("[\\[\\]]");
			return arrayBracketPattern.matcher(search).replaceAll("");
		}

	}

}
