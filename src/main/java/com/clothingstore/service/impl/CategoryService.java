package com.clothingstore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothingstore.Convert.CategoryConvert;
import com.clothingstore.DTO.CategoryDTO;
import com.clothingstore.entity.EntityCategory;
import com.clothingstore.repository.ICategoryRepository;
import com.clothingstore.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {
	
	@Autowired
	private ICategoryRepository categoryRepository;
	
	@Autowired
	private CategoryConvert convert;
	
	@Override
	public CategoryDTO findById(Long id) {
		Optional<EntityCategory> entity = categoryRepository.findById(id);
		if(!entity.isEmpty()) {
			return null;
		}
		return null;
	}
	
	@Override
	public CategoryDTO findByCategorySlug(String categorySlug) {
		CategoryDTO categoryDTO = null;
		List<EntityCategory> categories = categoryRepository.findByCategorySlug(categorySlug);
		if(categories != null && categories.size() > 0) {
			 categoryDTO = convert.toDTO(categories.get(0));
		}
		return categoryDTO;
	}
	
	@Override
	public List<CategoryDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	 @Override
	public CategoryDTO insert(CategoryDTO categoryDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	 
	 @Override
	public List<CategoryDTO> insertMultiCategory(List<CategoryDTO> categories) {
		// TODO Auto-generated method stub
		return null;
	}
	 
	 @Override
	public CategoryDTO update(CategoryDTO categoryDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	 
	 @Override
	public CategoryDTO delete(CategoryDTO categoryDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
