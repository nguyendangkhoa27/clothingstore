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
		return new MessageResponse<CategoryDTO>(HttpStatus.OK.value(),HttpStatus.OK,"Find category by id: "+id+" successfully",categoryService.findById(idLong));
	}
	
	@GetMapping("/api/category/all")
	public MessageResponse<List<CategoryDTO>> List(
			@RequestParam(name ="page", required = false) Integer page,
			@RequestParam(name ="size", required = false) Integer size) {
				Pageable pageable = null;
				if(page !=null && size !=null && page>0 && size >0) {
					page = page-1;
					pageable = PageRequest.of(page,size);
				}
		return new MessageResponse<List<CategoryDTO>>(HttpStatus.OK.value(),HttpStatus.OK,"Find All list category successfully",categoryService.findAll(pageable),categoryService.count(true));
	}
	
	@PostMapping("/api/category")
	public MessageResponse<CategoryDTO> save(@RequestBody CategoryDTO category){
		category.setId(null);
		return new MessageResponse<CategoryDTO>(HttpStatus.OK.value(),HttpStatus.OK,"save category successfully",categoryService.insert(category));
	}
	@PutMapping("/api/category")
	public MessageResponse<CategoryDTO> update(@RequestBody CategoryDTO category){
		return new MessageResponse<CategoryDTO>(HttpStatus.OK.value(),HttpStatus.OK,"update category successfully",categoryService.update(category));
	}
	
	@DeleteMapping("/api/category")
	public MessageResponse<Long> delete(@RequestBody List<Long> ids) {
		return new MessageResponse<Long>(HttpStatus.OK.value(),HttpStatus.OK,"Delete category successfully ",categoryService.delete(ids));
	}
	
	
}
