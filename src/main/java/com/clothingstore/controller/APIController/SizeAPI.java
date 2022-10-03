package com.clothingstore.controller.APIController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.clothingstore.DTO.SizeDTO;
import com.clothingstore.service.ISizeService;

@RestController(value="apiSizeOfAdmin")
public class SizeAPI {
	@Autowired
	private ISizeService sizeService; 
	
	@PostMapping("/api/size/search")
	public List<SizeDTO> list(@RequestBody SizeDTO sizeDTO){
		return sizeService.list(sizeDTO);
	}
	
	@PostMapping("/api/size")
	public SizeDTO save(@RequestBody SizeDTO sizeDTO){
		return sizeService.save(sizeDTO);
	}
	
	@PutMapping("/api/size")
	public SizeDTO update(@RequestBody SizeDTO sizeDTO){
		return sizeService.update(sizeDTO);
	}
	
	@DeleteMapping("/api/size")
	public Long delete(@RequestBody List<Long> ids){
		return sizeService.delete(ids);
	}
}
