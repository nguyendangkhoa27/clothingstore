package com.clothingstore.controller.APIController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clothingstore.DTO.ProductDTO;
import com.clothingstore.DTO.ProductList;
import com.clothingstore.service.IProductService;

@RestController(value="apiOfAdmin")
public class ProductAPI {

	
	@Autowired
	private IProductService productService;
	
	
	@GetMapping("/api/product")
	public List<ProductDTO> list(@RequestParam int idCategory) {
		Long idcate =(long)idCategory;
		List<ProductDTO> productDTOs = productService.findAllByCategory(idcate);
		return productDTOs;
	} 
	
	@PostMapping("/api/product/insert-multi")
	public List<ProductDTO> saveMultiProduct(@RequestBody ProductList listProduct){
		return productService.insertMultiProduct(listProduct.getList());
	}
	
	@PostMapping("/api/product")
	public ProductDTO createProduct(@RequestBody ProductDTO dto) {
			dto = productService.insert(dto);
		return dto;
	} 
	@PutMapping("/api/product")
	public ProductDTO updateProduct(@RequestBody ProductDTO dto) {
			dto = productService.update(dto);
		return dto;
	} 
	
	
}
