package com.clothingstore.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.clothingstore.DTO.ProductDTO;
import com.clothingstore.entity.EntityCategory;


public interface IProductService {
	
	public ProductDTO findOne(Long id);
	
	public List<ProductDTO> findAll(Pageable pageable);
	
	public List<ProductDTO> findAllByCategory(Long categoryId,Pageable pageable);
	
	public List<ProductDTO> findAllByCagorySlug(String categorySlug,Pageable pageable);
		
	public ProductDTO insert(ProductDTO productDTO);
	
	public ProductDTO update(ProductDTO productDTO);
	
	public List<ProductDTO> insertMultiProduct(List<ProductDTO> products);
	
	public Long deleteProduct(List<Long> ids);
	
	public List<ProductDTO> findByTitle(String title);
	Integer count(boolean isActive);
	Integer count(boolean isActive,String categorySlug);
}
