package com.clothingstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clothingstore.entity.EntityCategory;
import com.clothingstore.entity.EntityProduct;


public interface IProductRepository extends JpaRepository<EntityProduct, Long>  {
	
		List<EntityProduct> findAll();
		List<EntityProduct> findAllByCategory(EntityCategory category);
		@SuppressWarnings("unchecked")
		EntityProduct save(EntityProduct product);	
}
