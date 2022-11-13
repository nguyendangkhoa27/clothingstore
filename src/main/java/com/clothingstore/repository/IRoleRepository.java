package com.clothingstore.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clothingstore.entity.EntityRole;
@Transactional
public interface IRoleRepository extends JpaRepository<EntityRole, Long> {
	
	Long countByIsActive(boolean isActive);
	List<EntityRole> findByCode(String code);
	@Query(value = "select r from EntityRole r Where r.code IN(:codes)")
	List<EntityRole> findByRoleCodes(@Param("codes") List<String> codes);
	@Query(value = "select r from EntityRole r Where r.id IN(:ids)")
	List<EntityRole> findByRoleIds(@Param("ids") List<Long> ids);
}
