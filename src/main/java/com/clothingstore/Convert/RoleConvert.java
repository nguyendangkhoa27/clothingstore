package com.clothingstore.Convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.clothingstore.DTO.RoleDTO;
import com.clothingstore.entity.EntityRole;

@Component
public class RoleConvert implements abstractConvert<RoleDTO, EntityRole> {

	@Override
	public RoleDTO toDTO(EntityRole convert) {
		try {
			RoleDTO dto = new RoleDTO();
			dto.setId(convert.getId());
			dto.setCode(convert.getCode());
			dto.setName(convert.getName());
			dto.setIsActive(convert.getIsActive());
			dto.setCreatedBy(convert.getCreatedBy());
			dto.setCreatedDate(convert.getCreatedDate());
			dto.setModifiedDate(convert.getModifiedDate());
			dto.setModifiedBy(convert.getModifiedBy());
			return dto;
		}catch (Exception e) {
			throw e;
		}
	}

	@Override
	public EntityRole toEntity(RoleDTO convert) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityRole newToOld(EntityRole convert, EntityRole newold) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoleDTO> toDTO(List<EntityRole> entities) {
		try {
		List<RoleDTO> dtos = new ArrayList<>();
		for (EntityRole roleEntity : entities) {
			dtos.add(toDTO(roleEntity));
		}
		return dtos;
		}catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<EntityRole> toEntity(List<RoleDTO> DTOs) {
		// TODO Auto-generated method stub
		return null;
	}

}
