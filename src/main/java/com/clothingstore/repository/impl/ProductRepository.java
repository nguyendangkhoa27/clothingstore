package com.clothingstore.repository.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clothingstore.entity.EntityProduct;
import com.clothingstore.repository.IProductRepository;

@Component
public class ProductRepository {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private IProductRepository iProductRepository;
	
	@SuppressWarnings("unchecked")
	public List<EntityProduct> findAll(Long categoryId){
		String sql = "Select * from product where category_id = :categoryId";
		Query query = entityManager.createNativeQuery(sql, EntityProduct.class);
		query.setParameter("categoryId", categoryId);
		return query.getResultList();
		
	}
	
	public List<EntityProduct> findAll(){
		return iProductRepository.findAll();
	}
	
	public EntityProduct save(EntityProduct product) {
		return iProductRepository.save(product);
	}
	
	public EntityProduct findOne(Long id) {
		Optional<EntityProduct> e =	iProductRepository.findById(id); 
		if(!e.isEmpty()) {
			return e.get();
		}
		return null;
	}
	
	public List<EntityProduct> saveMultiProduct(List<EntityProduct> products){
		return iProductRepository.saveAll(products);
	}
}
