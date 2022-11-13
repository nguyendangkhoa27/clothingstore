package com.clothingstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothingstore.entity.EntityRole;
import com.clothingstore.repository.IRoleRepository;
import com.clothingstore.service.IRoleService;

@Service
public class RoleService implements IRoleService {
	@Autowired
	private IRoleRepository iRoleRepository;
	@Override
	public List<EntityRole> findByRoleCode(List<String> roles) {
		try {
		List<EntityRole> entityRoles = iRoleRepository.findByRoleCodes(roles);
		return entityRoles;
		}catch (Exception e) {
			throw e;
		}
	}
	@Override
	public List<EntityRole> findByRoleId(List<Long> roleIds) {
		try {
			List<EntityRole> entityRoles = iRoleRepository.findByRoleIds(roleIds);
			return entityRoles;
			}catch (Exception e) {
				throw e;
			}
	}
}
