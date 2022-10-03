package com.clothingstore.Convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.clothingstore.DTO.SizeDTO;
import com.clothingstore.entity.EntitySize;

@Component
public class SizeConvert {
	public SizeDTO toDTO(EntitySize entity) {
		SizeDTO dto = null;
		if(entity != null) {
			 dto = new SizeDTO();
			dto.setId(entity.getId());
			dto.setNameSize(entity.getNameSize());
			dto.setIsActive(entity.getIsActive());
			dto.setCreatedBy(entity.getCreatedBy());
			dto.setCreatedDate(entity.getCreatedDate());
			dto.setModifiedBy(entity.getModifiedBy());
			dto.setModifiedDate(entity.getModifiedDate());
		}
			return dto;
	}
	
	public EntitySize toEntity(SizeDTO dto) {
		EntitySize entity = null;
		if(dto != null) {
		 entity = new EntitySize();
		entity.setId(dto.getId());
		entity.setNameSize(dto.getNameSize());
		entity.setIsActive(dto.getIsActive());
		entity.setCreatedBy(dto.getCreatedBy());
		entity.setCreatedDate(dto.getCreatedDate());
		entity.setModifiedBy(dto.getModifiedBy());
		entity.setModifiedDate(dto.getModifiedDate());
		}
		return entity;
	}
	
	public List<SizeDTO> toSizeDTOs(List<EntitySize> entities){
		List<SizeDTO> dtos = null;
		if(entities != null && entities.size() > 0) {
			 dtos = new ArrayList<>();
			for (EntitySize entitySize : entities) {
				dtos.add(toDTO(entitySize));
			}
		}
		return dtos;
	}
	
	public List<EntitySize> toSizeEntites(List<SizeDTO> dtos){
		List<EntitySize> entities = null;
		if(dtos !=null && dtos.size() > 0) {
			entities = new ArrayList<>();
			for (SizeDTO sizeDTO : dtos) {
				entities.add(toEntity(sizeDTO));
			}
		}
		return entities;
	}
	
	public EntitySize toNewSize(EntitySize oldSize,EntitySize newSize) {
		if(oldSize !=null && newSize !=null) {
		oldSize.setNameSize(newSize.getNameSize());
		oldSize.setIsActive(newSize.getIsActive());
		oldSize.setCreatedBy(newSize.getCreatedBy());
		oldSize.setCreatedDate(newSize.getCreatedDate());
		oldSize.setModifiedBy(newSize.getModifiedBy());
		oldSize.setModifiedDate(newSize.getModifiedDate());
		}
		return oldSize;
	}
}
