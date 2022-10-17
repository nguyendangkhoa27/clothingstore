package com.clothingstore.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.clothingstore.DTO.SizeDTO;
import com.clothingstore.DTO.Short.SizeShortDTO;
import com.clothingstore.entity.EntitySize;

public interface ISizeService {
	
	List<SizeDTO> list(Pageable pageable);
	
	SizeDTO save(SizeDTO sizeDTO);
	
	SizeDTO update(SizeDTO sizeDTO);
	
	Long delete(List<Long> ids);
	
	List<SizeDTO> findBySizeName(List<String> sizeName);
	
	List<EntitySize> findSizeByIds(List<SizeShortDTO> sizeShorts);
	SizeDTO  findOne(Long id);
}
