package com.clothingstore.controller.APIController;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import com.clothingstore.DTO.ColorDTO;
import com.clothingstore.exception.BadRequestException;
import com.clothingstore.exception.MessageResponse;
import com.clothingstore.service.IColorService;
@CrossOrigin(origins = "*")
@RestController(value="apiColorOfAdmin")
public class ColorAPI {

	@Autowired
	private IColorService colorService; 
	
	@GetMapping("/api/color/all")
	public MessageResponse<List<ColorDTO>> list(
			@RequestParam(name ="page", required = false) Integer Page,
			@RequestParam(name ="size", required = false) Integer Size) {
				Pageable pageable = null;
				if(Page!=null && Size!=null) {
					pageable = PageRequest.of(Page,Size);
				}
		return new MessageResponse<List<ColorDTO>>(HttpStatus.OK.value(),HttpStatus.OK,"Success",colorService.list(pageable));
	}
	
	@GetMapping("/api/color/")
	public MessageResponse<ColorDTO> findOne(@RequestParam String id){
		Pattern pt = Pattern.compile("[^0-9]");
		Matcher mc = pt.matcher(id);
		boolean check = mc.find();
		if(check == false) {
			return new MessageResponse<ColorDTO>(HttpStatus.OK.value(),HttpStatus.OK,"Success",colorService.findOne(Long.parseLong(id)));
		}
		throw new BadRequestException("id không phải là chữ");
		
	}
	
	@PostMapping("/api/color")
	public MessageResponse<ColorDTO> save(@RequestBody ColorDTO color){
		return new MessageResponse<ColorDTO>(HttpStatus.OK.value(),HttpStatus.OK,"Success",colorService.save(color));
	}
	
	@PutMapping("/api/color")
	public MessageResponse<ColorDTO> update(@RequestBody ColorDTO color){
		return new MessageResponse<ColorDTO>(HttpStatus.OK.value(),HttpStatus.OK,"Success",colorService.update(color));
	}
	
	@DeleteMapping("/api/color")
	public MessageResponse<Long> delete(@RequestBody List<Long> ids){
		return new MessageResponse<Long>(HttpStatus.OK.value(),HttpStatus.OK,"Deleted",colorService.delete(ids));
	}
}
