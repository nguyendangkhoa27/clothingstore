package com.clothingstore.service;

import java.util.List;

import com.clothingstore.DTO.CategoryDTO;

public interface ICategoryService {
	CategoryDTO findById(Long id);
	List<CategoryDTO> findAll();
	CategoryDTO insert(CategoryDTO categoryDTO);
	CategoryDTO update(CategoryDTO categoryDTO);
	CategoryDTO delete(CategoryDTO categoryDTO);
	List<CategoryDTO> insertMultiCategory(List<CategoryDTO> categories);
	CategoryDTO findByCategorySlug(String categorySlug);
}
