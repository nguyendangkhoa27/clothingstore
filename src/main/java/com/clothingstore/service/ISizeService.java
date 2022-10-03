package com.clothingstore.service;

import java.util.List;

import com.clothingstore.DTO.SizeDTO;

public interface ISizeService {
	
	List<SizeDTO> list(SizeDTO sizeDTO);
	
	SizeDTO save(SizeDTO sizeDTO);
	
	SizeDTO update(SizeDTO sizeDTO);
	
	Long delete(List<Long> ids);
	
	List<SizeDTO> findBySizeName(List<String> sizeName);
}
