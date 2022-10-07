package com.clothingstore.controller.APIController;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clothingstore.DTO.SizeDTO;
import com.clothingstore.exception.BadRequestException;
import com.clothingstore.exception.MessageResponse;
import com.clothingstore.service.ISizeService;

@RestController(value="apiSizeOfAdmin")
public class SizeAPI {
	@Autowired
	private ISizeService sizeService; 
	
	@GetMapping("/api/size/all")
	public List<SizeDTO> list(){
		return sizeService.list();
	}
	@GetMapping("/api/size/")
	public MessageResponse<SizeDTO> findOne(@RequestParam String id){
		Pattern pt = Pattern.compile("[^0-9]");
		Matcher mc = pt.matcher(id);
		boolean check = mc.find();
		if(check == false) {
			return new MessageResponse<SizeDTO>(HttpStatus.OK.value(),HttpStatus.OK,"Thành công",sizeService.findOne(Long.parseLong(id)));
		}
		throw new BadRequestException("id không phải là chữ");
		
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
