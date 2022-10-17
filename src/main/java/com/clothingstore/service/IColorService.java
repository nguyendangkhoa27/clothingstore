package com.clothingstore.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.clothingstore.DTO.ColorDTO;
import com.clothingstore.DTO.Short.ColorShortDTO;
import com.clothingstore.entity.EntityColor;

public interface IColorService {

	List<ColorDTO> list(Pageable pageable);
	
	ColorDTO save(ColorDTO color);
	
	ColorDTO update(ColorDTO color);
	
	Long delete(List<Long> ids);
	
	List<EntityColor> findByColorName(List<String> colorNames);
	
	List<EntityColor> findByColorIds(List<ColorShortDTO> colorShorts);
	
	ColorDTO findOne(Long id);
}
