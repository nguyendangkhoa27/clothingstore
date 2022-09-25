package com.clothingstore.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothingstore.entity.EntityCategory;
import com.clothingstore.repository.ICategoryRepository;
import com.clothingstore.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {
	
	@Autowired
	private ICategoryRepository categoryRepository;
	
	@Override
	public EntityCategory findById(Long id) {
		Optional<EntityCategory> entity = categoryRepository.findById(id);
		if(!entity.isEmpty()) {
			return entity.get();
		}
		return null;
	}
}
