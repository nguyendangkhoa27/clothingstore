package com.clothingstore.service;

import java.util.List;

import com.clothingstore.DTO.ProductDTO;


public interface IProductService {
	
	public ProductDTO findOne(Long id);
	
	public List<ProductDTO> findAll();
	
	public List<ProductDTO> findAllByCategory(Long categoryId);
	
	public List<ProductDTO> findAllByCagorySlug(String categorySlug);
		
	public ProductDTO insert(ProductDTO productDTO);
	
	public ProductDTO update(ProductDTO productDTO);
	
	public List<ProductDTO> insertMultiProduct(List<ProductDTO> products);
	
	public Long deleteProduct(List<Long> ids);
}
