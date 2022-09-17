package com.recipe.business.dto;

import java.util.List;

import org.springframework.data.domain.Sort;

import lombok.Data;

@Data
public class SortDto {
	private Sort.Direction sortOrder;
	private List<String> sortFields;
}
