package com.clothingstore.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clothingstore.entity.EntityColor;

@Transactional
public interface IColorRepository extends JpaRepository<EntityColor, Long> {
	
	@Modifying
	@Query(value="UPDATE color SET is_active = false WHERE id IN (:ids)",nativeQuery = true)
	public int deleteColor(@Param("ids") List<Long> ids);
	
	@Modifying
	@Query(value="Select * from color WHERE is_active = true AND color_name IN (:names)",nativeQuery = true)
	public List<EntityColor> findByColorNames(@Param("names") List<String> colorNames);
	
	@Query(value="Select * from color WHERE is_active = true AND id IN (:ids)",nativeQuery = true)
	public List<EntityColor> findByColorIds(@Param("ids") List<Long> ids);
	
	List<EntityColor> findByIsActiveOrderByIdAsc(Pageable pageable,boolean isActive);
	
	Long countByIsActive(boolean isActive);
}
