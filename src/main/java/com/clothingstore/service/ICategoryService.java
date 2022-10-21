package com.clothingstore.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.clothingstore.DTO.CategoryDTO;

public interface ICategoryService {
	CategoryDTO findById(Long id);
	List<CategoryDTO> findAll(Pageable pageable);
	CategoryDTO insert(CategoryDTO categoryDTO);
	CategoryDTO update(CategoryDTO categoryDTO);
	Long delete(List<Long> ids);
	List<CategoryDTO> insertMultiCategory(List<CategoryDTO> categories);
	CategoryDTO findByCategorySlug(String categorySlug);
	Integer count(boolean isActive);
	
}
