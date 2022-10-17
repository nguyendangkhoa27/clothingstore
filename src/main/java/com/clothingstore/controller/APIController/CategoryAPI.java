package com.clothingstore.controller.APIController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clothingstore.DTO.CategoryDTO;
import com.clothingstore.exception.MessageResponse;
import com.clothingstore.service.ICategoryService;
@CrossOrigin(origins = "*")
@RestController(value = "apiCategoryOfAdmin")
public class CategoryAPI {

	
	@Autowired
	private ICategoryService categoryService;
	
	@GetMapping("/api/category")
	public MessageResponse<CategoryDTO> findOne(@RequestParam String id){
		Long idLong = Long.parseLong(id);
		return new MessageResponse<CategoryDTO>(HttpStatus.OK.value(),HttpStatus.OK,"Success",categoryService.findById(idLong));
	}
	
	@GetMapping("/api/category/all")
	public MessageResponse<List<CategoryDTO>> List(
			@RequestParam(name ="page", required = false) Integer Page,
			@RequestParam(name ="size", required = false) Integer Size) {
				Pageable pageable = null;
				if(Page!=null && Size!=null) {
					pageable = PageRequest.of(Page,Size);
				}
		return new MessageResponse<List<CategoryDTO>>(HttpStatus.OK.value(),HttpStatus.OK,"Success",categoryService.findAll(pageable));
	}
	
	@PostMapping("/api/category")
	public MessageResponse<CategoryDTO> save(@RequestBody CategoryDTO category){
		category.setId(null);
		return new MessageResponse<CategoryDTO>(HttpStatus.OK.value(),HttpStatus.OK,"Success",categoryService.insert(category));
	}
	@PutMapping("/api/category")
	public MessageResponse<CategoryDTO> update(@RequestBody CategoryDTO category){
		return new MessageResponse<CategoryDTO>(HttpStatus.OK.value(),HttpStatus.OK,"Success",categoryService.update(category));
	}
	
	@DeleteMapping("/api/category")
	public MessageResponse<Long> delete(@RequestBody List<Long> ids) {
		return new MessageResponse<Long>(HttpStatus.OK.value(),HttpStatus.OK,"Deleted",categoryService.delete(ids));
	}
	
	
}
