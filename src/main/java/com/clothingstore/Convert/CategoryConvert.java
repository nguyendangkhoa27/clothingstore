package com.clothingstore.Convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.clothingstore.DTO.CategoryDTO;
import com.clothingstore.entity.EntityCategory;


@Component
public class CategoryConvert {
	
	
	public CategoryDTO toDTO(EntityCategory eCategory) {
		CategoryDTO dto = null;
		if(eCategory !=null) {
			dto = new CategoryDTO();
			dto.setId(eCategory.getId());
			dto.setCategorySlug(eCategory.getCategorySlug());
			dto.setIsActive(eCategory.getIsActive());
			if(eCategory.getImg() !=null && !eCategory.getImg().isEmpty()) {
				String[] img = eCategory.getImg().split(",");
				List<String> imgs = new ArrayList<>();
				for (String item : img) {
					imgs.add(item);
				}
				dto.setImg(imgs);
			}
		
		}
		dto.setCreatedBy(eCategory.getCreatedBy());
		dto.setCreatedDate(eCategory.getCreatedDate());
		dto.setModifiedBy(eCategory.getModifiedBy());
		dto.setModifiedDate(eCategory.getModifiedDate());
		return dto;
	}
	
	public EntityCategory toEntity(CategoryDTO dto) {
		EntityCategory entity = null;
		if(dto != null) {
			entity = new EntityCategory();
			entity.setId(dto.getId());
			entity.setCategorySlug(dto.getCategorySlug());
			if(dto.getImg() != null && dto.getImg().size() > 0) {
				String imgs = "";
				for(String img : dto.getImg()) {
					imgs += img+",";
				} 
				entity.setImg(imgs.substring(0,(imgs.length()-1)));
			}
			if(dto.getIsActive() == null) {
				entity.setIsActive(true);
			}else {
				entity.setIsActive(dto.getIsActive());
			}
			entity.setCreatedBy(dto.getCreatedBy());
			entity.setCreatedDate(dto.getCreatedDate());
			entity.setModifiedBy(dto.getModifiedBy());
			entity.setModifiedDate(dto.getModifiedDate());
		}
		return entity;
	}
	public List<CategoryDTO> toListDTO(List<EntityCategory> categoriesEn){
		List<CategoryDTO> listDTO = new ArrayList<>();
		for (EntityCategory entity : categoriesEn) {
			listDTO.add(toDTO(entity));
		}
		return listDTO;
	}
	public List<EntityCategory> toListEntity(List<CategoryDTO> categoriesDTO){
		List<EntityCategory> listEntity = new ArrayList<>();
		for (CategoryDTO dto : categoriesDTO) {
			listEntity.add(toEntity(dto));
		}
		return listEntity;
	}
	
	public EntityCategory toNewCate(EntityCategory oldCate, EntityCategory newCate) {
		oldCate.setCategorySlug(newCate.getCategorySlug());
		oldCate.setIsActive(newCate.getIsActive());
		oldCate.setImg(newCate.getImg());
		oldCate.setCreatedBy(newCate.getCreatedBy());
		oldCate.setCreatedDate(newCate.getCreatedDate());
		oldCate.setModifiedBy(newCate.getModifiedBy());
		oldCate.setModifiedDate(newCate.getModifiedDate());
		return oldCate;
	}
}
