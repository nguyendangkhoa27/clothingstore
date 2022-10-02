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
		return dto;
	}
	
	public EntityCategory toEntity(CategoryDTO dto) {
		EntityCategory entity = null;
		if(dto != null) {
			entity = new EntityCategory();
			entity.setId(dto.getId());
			entity.setCategorySlug(dto.getCategorySlug());
			entity.setIsActive(dto.getIsActive());
			if(dto.getImg() != null && dto.getImg().size() > 0) {
				String img = dto.getImg().toString(); 
				entity.setImg(img);
			}
			
		}
		return entity;
	}
	
}
