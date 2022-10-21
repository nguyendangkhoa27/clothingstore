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

import com.clothingstore.DTO.SizeDTO;
import com.clothingstore.exception.BadRequestException;
import com.clothingstore.exception.MessageResponse;
import com.clothingstore.service.ISizeService;
@CrossOrigin(origins = "*")
@RestController(value="apiSizeOfAdmin")
public class SizeAPI {
	@Autowired
	private ISizeService sizeService; 
	
	@GetMapping("/api/size/all")
	public MessageResponse<List<SizeDTO>> list(
			@RequestParam(name ="page", required = false) Integer page,
			@RequestParam(name ="size", required = false) Integer size) {
				Pageable pageable = null;
				if(page !=null && size !=null && page>0 && size >0) {
					page = page-1;
					pageable = PageRequest.of(page,size);
				}
		return new MessageResponse<List<SizeDTO>>(HttpStatus.OK.value(),HttpStatus.OK,"Find all size successfully", sizeService.list(pageable),sizeService.count(true));
	}
	@GetMapping("/api/size/")
	public MessageResponse<SizeDTO> findOne(@RequestParam String id){
		Pattern pt = Pattern.compile("[^0-9]");
		Matcher mc = pt.matcher(id);
		boolean check = mc.find();
		if(check == false) {
			return new MessageResponse<SizeDTO>(HttpStatus.OK.value(),HttpStatus.OK,"Find size by id :"+id+"successfully",sizeService.findOne(Long.parseLong(id)));
		}
		throw new BadRequestException("id not found");
		
	}
	@PostMapping("/api/size")
	public MessageResponse<SizeDTO> save(@RequestBody SizeDTO sizeDTO){
		return new MessageResponse<SizeDTO>(HttpStatus.OK.value(),HttpStatus.OK,"Save size successfully",sizeService.save(sizeDTO));
	}
	
	@PutMapping("/api/size")
	public MessageResponse<SizeDTO> update(@RequestBody SizeDTO sizeDTO){
		return new MessageResponse<SizeDTO>(HttpStatus.OK.value(),HttpStatus.OK,"Update size successfully",sizeService.update(sizeDTO));
	}
	
	@DeleteMapping("/api/size")
	public MessageResponse<Long> delete(@RequestBody List<Long> ids){
		return new MessageResponse<Long>(HttpStatus.OK.value(),HttpStatus.OK,"Delete Size successfully",sizeService.delete(ids));
	}
}
