package com.clothingstore.controller.APIController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.clothingstore.DTO.ColorDTO;
import com.clothingstore.service.IColorService;

@RestController(value="apiColorOfAdmin")
public class ColorAPI {

	@Autowired
	private IColorService colorService; 
	
	@PostMapping("/api/color/search")
	public List<ColorDTO> list(@RequestBody ColorDTO color){
		return colorService.list(color);
	}
	
	@PostMapping("/api/color")
	public ColorDTO save(@RequestBody ColorDTO color){
		return colorService.save(color);
	}
	
	@PutMapping("/api/color")
	public ColorDTO update(@RequestBody ColorDTO color){
		return colorService.update(color);
	}
	
	@DeleteMapping("/api/color")
	public Long delete(@RequestBody List<Long> ids){
		return colorService.delete(ids);
	}
}
