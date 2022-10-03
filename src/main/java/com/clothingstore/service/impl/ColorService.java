package com.clothingstore.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothingstore.Convert.ColorConvert;
import com.clothingstore.DTO.ColorDTO;
import com.clothingstore.entity.EntityColor;
import com.clothingstore.repository.IColorRepository;
import com.clothingstore.repository.impl.CustomColorRepository;
import com.clothingstore.service.IColorService;

@Service
public class ColorService implements IColorService {
	
	@Autowired
	private IColorRepository colorRepository;
	
	@Autowired
	private CustomColorRepository CustomColorRepository;
	
	@Autowired
	private ColorConvert colorConvert;
	
	@Override
	public List<ColorDTO> list(ColorDTO colorSearch) {
		return colorConvert.toListDTO(CustomColorRepository.list(colorSearch));
	}
	
	@Override
	public ColorDTO save(ColorDTO color) {
		color.setId(null);
		color.setCreatedDate(new Date());
		return  colorConvert.toDTO(colorRepository.save(colorConvert.toEntity(color)));
	}
	
	@Override
	public ColorDTO update(ColorDTO color) {
		EntityColor oldColor = colorRepository.findById(color.getId()).get();
		EntityColor newColor = colorConvert.toEntity(color);
		oldColor = colorConvert.toNewColor(oldColor, newColor);
		color.setModifiedDate(new Date());
		return colorConvert.toDTO(colorRepository.save(oldColor));
	}
	
	@Override
	public Long delete(List<Long> ids) {
		return (long) colorRepository.deleteColor(ids);
	}
	@Override
	public List<EntityColor> findByColorName(List<String> colorNames) {
		
		return colorRepository.findByColorNames(colorNames);
	}
	
}
