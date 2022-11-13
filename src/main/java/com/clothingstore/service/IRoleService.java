package com.clothingstore.service;

import java.util.List;

import com.clothingstore.DTO.RoleDTO;
import com.clothingstore.entity.EntityRole;

public interface IRoleService {
	public List<EntityRole> findByRoleCode(List<String> roleNames);
	public List<EntityRole> findByRoleId(List<Long> roleIds);
}
