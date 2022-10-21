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
			return new MessageResponse<ProductDTO>(HttpStatus.OK.value(),HttpStatus.OK,"Find product by id: "+id+" successfully",productDTO);
		}
		throw new BadRequestException("id not found!");
	}
	
	@ApiOperation(value = "Finds Product by idCategory or CategorySlug",
		    notes = "param is Long or String *ex: ?param=1 or ?param=T-Shirt ",
		    response = ProductDTO.class,
		    responseContainer = "List")
	@GetMapping("/api/product/")
	public MessageResponse<List<ProductDTO>> list(
			@RequestParam String param ,
			@RequestParam(name ="page", required = false) Integer page,
			@RequestParam(name ="size", required = false) Integer size) {
				Pageable pageable = null;
				if(page !=null && size !=null && page>0 && size >0) {
					page = page-1;
					pageable = PageRequest.of(page,size);
				}
				int count = 0;
				List<ProductDTO> productDTOs = null;
					Pattern pattern = Pattern.compile("[^0-9]");// regex [^0-9] tìm những kí tự không phải là số 
					Matcher mattcher = pattern.matcher(param);
					boolean check = mattcher.find();//check
					if(check) {//nếu bằng true => param có chữ 
						productDTOs = productService.findAllByCagorySlug(param,pageable);
					}else {
						Long idcate =Long.parseLong(param);
						productDTOs = productService.findAllByCategory(idcate,pageable);
						count = productService.count(true, productDTOs.get(0).getCategorySlug().getCategoryName());
					}
				
				return new MessageResponse<List<ProductDTO>>(HttpStatus.OK.value(),HttpStatus.OK,"Find product by category successfully",productDTOs,count);
	} 
	@ApiOperation(value = "Finds Product by object Search Product",
		    notes = "object{ id,name,price,category,is_active}",
		    response = ProductDTO.class,
		    responseContainer = "List")

	@GetMapping("/api/product/all")
	public MessageResponse<List<ProductDTO>> list(
			@RequestParam(name ="page", required = false) Integer page,
			@RequestParam(name ="size", required = false) Integer size) {
				Pageable pageable = null;
				if( page !=null && size !=null && page>0 && size >0) {
					page = page-1;
					pageable = PageRequest.of(page,size);
				}
		List<ProductDTO> productDTOs =  productService.findAll(pageable);
		return new MessageResponse<List<ProductDTO>>(HttpStatus.OK.value(),HttpStatus.OK,"Find all product successfully",productDTOs,productService.count(true));
	} 
	
//	@PostMapping("/api/product/insert-multi")
//	public List<ProductDTO> saveMultiProduct(@RequestBody ProductList listProduct){
//		return productService.insertMultiProduct(listProduct.getList());
//	}
	
	@PostMapping("/api/product")
	public MessageResponse<ProductDTO> createProduct(@RequestBody ProductDTO dto) {
			dto.setId(null);
			dto = productService.insert(dto);
		return new MessageResponse<ProductDTO>(HttpStatus.OK.value(),HttpStatus.OK,"Save product successfully",dto);
	} 
	@PutMapping("/api/product")
	public MessageResponse<ProductDTO> updateProduct(@RequestBody ProductDTO dto) {
			dto = productService.update(dto);
		return new MessageResponse<ProductDTO>(HttpStatus.OK.value(),HttpStatus.OK,"Update product successfully",dto);
	} 
	
	@DeleteMapping("/api/product")
	public MessageResponse<Long> deleteProduct(@RequestBody List<Long> ids) {
		return new MessageResponse<Long>(HttpStatus.OK.value(),HttpStatus.OK,"Delete product successfully",productService.deleteProduct(ids));
	}
	
	
}
