package com.clothingstore.controller.APIController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clothingstore.DTO.CategoryDTO;
import com.clothingstore.service.ICategoryService;

@RestController(value = "apiCategoryOfAdmin")
public class CategoryAPI {

	
	@Autowired
	private ICategoryService categoryService;
	
	@GetMapping("/api/category")
	public CategoryDTO findOne(@RequestParam String id){
		Long idLong = Long.parseLong(id);
		return categoryService.findById(idLong);
	}
	
	@GetMapping("/api/category/all")
	public List<CategoryDTO> List(){
		return categoryService.findAll();
	}
	
	@PostMapping("/api/category")
	public CategoryDTO save(@RequestBody CategoryDTO category){
		category.setId(null);
		return categoryService.insert(category);
	}
	@PutMapping("/api/category")
	public CategoryDTO update(@RequestBody CategoryDTO category){
		return categoryService.update(category);
	}
	
	@DeleteMapping("/api/category")
	public Long delete(@RequestBody List<Long> ids) {
		return categoryService.delete(ids);
	}
	
	
}
