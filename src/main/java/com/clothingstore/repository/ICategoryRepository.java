package com.clothingstore.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clothingstore.entity.EntityCategory;

@Transactional
public interface ICategoryRepository extends JpaRepository<EntityCategory, Long> {
	List<EntityCategory> findByIsActive(boolean isActive);
	List<EntityCategory> findByCategorySlug(String categorySlug);
	@Modifying
	@Query(value = "update category set is_active = false where id IN (:ids)", nativeQuery = true)
	public int deleteWithMultiId(@Param("ids") List<Long> ids);
}
