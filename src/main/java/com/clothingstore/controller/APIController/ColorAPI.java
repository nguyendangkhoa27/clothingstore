package com.clothingstore.controller.APIController;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clothingstore.DTO.ColorDTO;
import com.clothingstore.exception.BadRequestException;
import com.clothingstore.service.IColorService;

@RestController(value="apiColorOfAdmin")
public class ColorAPI {

	@Autowired
	private IColorService colorService; 
	
	@GetMapping("/api/color/all")
	public List<ColorDTO> list(){
		return colorService.list();
	}
	
	@GetMapping("/api/color/")
	public ColorDTO findOne(@RequestParam String id){
		Pattern pt = Pattern.compile("[^0-9]");
		Matcher mc = pt.matcher(id);
		boolean check = mc.find();
		if(check == false) {
			return colorService.findOne(Long.parseLong(id));
		}
		throw new BadRequestException("id không phải là chữ");
		
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
