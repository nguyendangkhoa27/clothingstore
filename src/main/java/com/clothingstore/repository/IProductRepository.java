package com.clothingstore.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clothingstore.entity.EntityCategory;
import com.clothingstore.entity.EntityProduct;

@Transactional
public interface IProductRepository extends JpaRepository<EntityProduct, Long>  {
	
		List<EntityProduct> findAll();
		
		List<EntityProduct> findAllByCategory(EntityCategory category);
		@SuppressWarnings("unchecked")
		EntityProduct save(EntityProduct product);
		
		@Modifying
		@Query(value="Update product Set is_active = false WHERE id IN (:ids)",nativeQuery = true)
		public int deleteProduct(@Param("ids") List<Long> ids);
		
		@Query(value="Select * from product where is_active = true AND category_id = :idCate" , nativeQuery=true)
		List<EntityProduct> findByIdCategory(@Param("idCate") Long idCate);
}
