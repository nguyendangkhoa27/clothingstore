package com.clothingstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clothingstore.DTO.ProductDTO;
import com.clothingstore.entity.EntityProduct;

@Service
public interface IProductService {
	
	public ProductDTO findOne(Long id);
	
	public List<ProductDTO> findAll();
	
	public List<ProductDTO> findAllByCategory(Long categoryId);
	
	public List<ProductDTO> findAllByCagorySlug(String categorySlug);
		
	public ProductDTO insert(ProductDTO productDTO);
	
	public ProductDTO update(ProductDTO productDTO);
	
	public List<ProductDTO> insertMultiProduct(List<ProductDTO> products);
}
