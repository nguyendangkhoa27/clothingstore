package com.clothingstore.controller.APIController;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clothingstore.DTO.ProductDTO;
import com.clothingstore.DTO.ProductList;
import com.clothingstore.service.IProductService;

import io.swagger.annotations.ApiOperation;

@RestController(value="apiProductOfAdmin")
public class ProductAPI {

	
	@Autowired
	private IProductService productService;
	
	@ApiOperation(value = "Finds Product by idCategory or CategorySlug",
		    notes = "param is Long or String *ex: ?param=1 or ?param=T-Shirt ",
		    response = ProductDTO.class,
		    responseContainer = "List")
	@GetMapping("/api/product")
	public List<ProductDTO> list(@RequestParam String param) {
		List<ProductDTO> productDTOs = null;
			Pattern pattern = Pattern.compile("[^0-9]");// regex [^0-9] tìm những kí tự không phải là số 
			Matcher mattcher = pattern.matcher(param);
			boolean check = mattcher.find();//check
			if(check) {//nếu bằng true => param có chữ 
				productDTOs = productService.findAllByCagorySlug(param);
			}else {
			Long idcate =Long.parseLong(param);
			productDTOs = productService.findAllByCategory(idcate);
			}
		
		return productDTOs;
	} 
	@ApiOperation(value = "Finds Product by object Search Product",
		    notes = "object{ id,name,price,category,is_active}",
		    response = ProductDTO.class,
		    responseContainer = "List")

	@GetMapping("/api/product/all")
	public List<ProductDTO> list() {
		List<ProductDTO> productDTOs =  productService.findAll();
		return productDTOs;
	} 
	
	@PostMapping("/api/product/insert-multi")
	public List<ProductDTO> saveMultiProduct(@RequestBody ProductList listProduct){
		return productService.insertMultiProduct(listProduct.getList());
	}
	
	@PostMapping("/api/product")
	public ProductDTO createProduct(@RequestBody ProductDTO dto) {
			dto.setId(null);
			dto = productService.insert(dto);
		return dto;
	} 
	@PutMapping("/api/product")
	public ProductDTO updateProduct(@RequestBody ProductDTO dto) {
			dto = productService.update(dto);
		return dto;
	} 
	
	@DeleteMapping("/api/product")
	public Long deleteProduct(@RequestBody List<Long> ids) {
		return productService.deleteProduct(ids);
	}
	
	
}
