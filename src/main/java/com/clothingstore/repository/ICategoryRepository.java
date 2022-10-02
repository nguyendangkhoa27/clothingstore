package com.clothingstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clothingstore.entity.EntityCategory;

public interface ICategoryRepository extends JpaRepository<EntityCategory, Long> {
	
	List<EntityCategory> findByCategorySlug(String categorySlug);
}
