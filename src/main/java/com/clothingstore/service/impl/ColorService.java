package com.clothingstore.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothingstore.Convert.ColorConvert;
import com.clothingstore.DTO.ColorDTO;
import com.clothingstore.DTO.Short.ColorShortDTO;
import com.clothingstore.entity.EntityColor;
import com.clothingstore.exception.BadRequestException;
import com.clothingstore.exception.NotFoundException;
import com.clothingstore.repository.IColorRepository;
import com.clothingstore.service.IColorService;

import Message.message;

@Service
public class ColorService implements IColorService {
	
	@Autowired
	private IColorRepository colorRepository;
	

	@Autowired
	private ColorConvert colorConvert;
	
	@Override
	public List<ColorDTO> list() {
		return colorConvert.toListDTO(colorRepository.findByIsActive(true));
	}
	
	@Override
	public ColorDTO save(ColorDTO color) {
		try {
			color.setId(null);
			color.setCreatedDate(new Date());
			return  colorConvert.toDTO(colorRepository.save(colorConvert.toEntity(color)));
		}catch(Exception e) {
			e.printStackTrace();
			throw new BadRequestException(message.messageBadRequest);
		}
	}
	
	@Override
	public ColorDTO update(ColorDTO color) {
		try {
			EntityColor oldColor = colorRepository.findById(color.getId()).get();
			EntityColor newColor = colorConvert.toEntity(color);
			oldColor = colorConvert.toNewColor(oldColor, newColor);
			color.setModifiedDate(new Date());
			return colorConvert.toDTO(colorRepository.save(oldColor));
		}catch(Exception e) {
			e.printStackTrace();
			throw new BadRequestException(message.messageBadRequest);
		}
	}
	
	@Override
	public Long delete(List<Long> ids) {
		 int i = colorRepository.deleteColor(ids);
			if(i > 0) {
				return (long) i;
			}
			throw new NotFoundException("Không có sản phẩm này");
		
	}
	@Override
	public List<EntityColor> findByColorName(List<String> colorNames) {
		
		return colorRepository.findByColorNames(colorNames);
	}
	
	@Override
	public List<EntityColor> findByColorIds(List<ColorShortDTO> colorShorts) {
		List<Long> ids = new ArrayList<>();
		for (ColorShortDTO colorShortDTO : colorShorts) {
			ids.add(colorShortDTO.getId());
		}
		return colorRepository.findByColorIds(ids);
	}
	
	@Override
	public ColorDTO findOne(Long id) {
			try {
				ColorDTO dto = colorConvert.toDTO(colorRepository.findById(id).get());
				if(dto !=null ) {
					return dto;
				}
				throw new NotFoundException("Không có màu này!");
			}catch(Exception e) {
				throw new BadRequestException(message.messageBadRequest);
			}
			
		}
	
}
