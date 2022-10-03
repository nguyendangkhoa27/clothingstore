package com.clothingstore.service.impl;

import java.util.Date;
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
			return convert.toDTO(entity.get());
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
		return convert.toListDTO(categoryRepository.findAll());
	}
	
	 @Override
	public CategoryDTO insert(CategoryDTO categoryDTO) {
		EntityCategory entity = convert.toEntity(categoryDTO);
		entity.setCreatedDate(new Date());
		entity = categoryRepository.save(entity);
		return convert.toDTO(entity);
	}
	 
	 @Override
	public List<CategoryDTO> insertMultiCategory(List<CategoryDTO> categories) {
		 List<EntityCategory> listEntity = null;
		if(categories !=null && categories.size() > 0) {
			listEntity = convert.toListEntity(categories);
			for( EntityCategory cate : listEntity) {
				cate.setCreatedDate(new Date());
			}
		categories = convert.toListDTO(categoryRepository.saveAll(listEntity));
		}
		return categories;
	}
	 
	 @Override
	public CategoryDTO update(CategoryDTO categoryDTO) {
		if (categoryDTO != null) {
			EntityCategory oldCate = convert.toEntity(findById(categoryDTO.getId()));
			EntityCategory newCate = convert.toEntity(categoryDTO);
			if(oldCate != null) {
				oldCate = convert.toNewCate(oldCate, newCate);
				oldCate.setModifiedDate(new Date());
				oldCate = categoryRepository.save(oldCate);
				return convert.toDTO(oldCate);
			}
		}
		return null;
	}
	 
	 @Override
		public Long delete(List<Long> ids) {			 
			return (long) categoryRepository.deleteWithMultiId(ids);
		}
	
}
