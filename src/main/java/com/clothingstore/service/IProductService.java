package com.clothingstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clothingstore.DTO.ProductDTO;
import com.clothingstore.entity.EntityProduct;

@Service
public interface IProductService {
	
	public List<ProductDTO> findAll(Long id);
	
	public List<ProductDTO> findAllByCategory(Long categoryId);
		
	public ProductDTO insert(ProductDTO productDTO);
	
	public ProductDTO update(ProductDTO productDTO);
	
	public EntityProduct findOne(Long id);
	
	public List<ProductDTO> insertMultiProduct(List<ProductDTO> products);
}
