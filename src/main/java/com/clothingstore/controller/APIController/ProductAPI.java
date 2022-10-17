package com.clothingstore.controller.APIController;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clothingstore.DTO.ProductDTO;
import com.clothingstore.exception.BadRequestException;
import com.clothingstore.exception.MessageResponse;
import com.clothingstore.service.IProductService;

import io.swagger.annotations.ApiOperation;
@CrossOrigin(origins = "*")
@RestController(value="apiProductOfAdmin")
public class ProductAPI {

	
	@Autowired
	private IProductService productService;
	
	@GetMapping("/api/product/detail")
	public MessageResponse<ProductDTO> findOne(@RequestParam String id) {
		ProductDTO productDTO = null;
		Pattern pt = Pattern.compile("[^0-9]");
		Matcher mc = pt.matcher(id);
		boolean check = mc.find();
		if(check == false) {
			Long idLong = Long.parseLong(id);
			productDTO = productService.findOne(idLong);
			return new MessageResponse<ProductDTO>(HttpStatus.OK.value(),HttpStatus.OK,"Success",productDTO);
		}
		throw new BadRequestException("id không phải là chữ");
	}
	
	@ApiOperation(value = "Finds Product by idCategory or CategorySlug",
		    notes = "param is Long or String *ex: ?param=1 or ?param=T-Shirt ",
		    response = ProductDTO.class,
		    responseContainer = "List")
	@GetMapping("/api/product/")
	public MessageResponse<List<ProductDTO>> list(
			@RequestParam String param ,
			@RequestParam(name ="page", required = false) Integer Page,
			@RequestParam(name ="size", required = false) Integer Size) {
				Pageable pageable = null;
				if(Page!=null && Size!=null) {
					pageable = PageRequest.of(Page,Size);
				}
				List<ProductDTO> productDTOs = null;
					Pattern pattern = Pattern.compile("[^0-9]");// regex [^0-9] tìm những kí tự không phải là số 
					Matcher mattcher = pattern.matcher(param);
					boolean check = mattcher.find();//check
					if(check) {//nếu bằng true => param có chữ 
						productDTOs = productService.findAllByCagorySlug(param,pageable);
					}else {
						Long idcate =Long.parseLong(param);
						productDTOs = productService.findAllByCategory(idcate,pageable);
					}
				
				return new MessageResponse<List<ProductDTO>>(HttpStatus.OK.value(),HttpStatus.OK,"Success",productDTOs);
	} 
	@ApiOperation(value = "Finds Product by object Search Product",
		    notes = "object{ id,name,price,category,is_active}",
		    response = ProductDTO.class,
		    responseContainer = "List")

	@GetMapping("/api/product/all")
	public MessageResponse<List<ProductDTO>> list(
			@RequestParam(name ="page", required = false) Integer Page,
			@RequestParam(name ="size", required = false) Integer Size) {
		Pageable pageable = null;
		if(Page!=null && Size!=null) {
			pageable = PageRequest.of(Page,Size);
		}
		List<ProductDTO> productDTOs =  productService.findAll(pageable);
		return new MessageResponse<List<ProductDTO>>(HttpStatus.OK.value(),HttpStatus.OK,"Success",productDTOs);
	} 
	
//	@PostMapping("/api/product/insert-multi")
//	public List<ProductDTO> saveMultiProduct(@RequestBody ProductList listProduct){
//		return productService.insertMultiProduct(listProduct.getList());
//	}
	
	@PostMapping("/api/product")
	public MessageResponse<ProductDTO> createProduct(@RequestBody ProductDTO dto) {
			dto.setId(null);
			dto = productService.insert(dto);
		return new MessageResponse<ProductDTO>(HttpStatus.OK.value(),HttpStatus.OK,"Success",dto);
	} 
	@PutMapping("/api/product")
	public MessageResponse<ProductDTO> updateProduct(@RequestBody ProductDTO dto) {
			dto = productService.update(dto);
		return new MessageResponse<ProductDTO>(HttpStatus.OK.value(),HttpStatus.OK,"Success",dto);
	} 
	
	@DeleteMapping("/api/product")
	public MessageResponse<Long> deleteProduct(@RequestBody List<Long> ids) {
		return new MessageResponse<Long>(HttpStatus.OK.value(),HttpStatus.OK,"Success",productService.deleteProduct(ids));
	}
	
	
}
