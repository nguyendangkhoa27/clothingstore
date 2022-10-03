package com.clothingstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothingstore.Convert.SizeConvert;
import com.clothingstore.DTO.SizeDTO;
import com.clothingstore.entity.EntitySize;
import com.clothingstore.repository.ISizeRepository;
import com.clothingstore.repository.impl.CustomSizeRepository;
import com.clothingstore.service.ISizeService;

@Service
public class SizeService implements ISizeService {

	
	@Autowired
	private ISizeRepository iSizeRepository;
	
	@Autowired
	private CustomSizeRepository customSizeRepository;
	
	@Autowired
	private SizeConvert convert;
	 @Override
	public List<SizeDTO> list(SizeDTO sizeDTO) {
		return convert.toSizeDTOs(customSizeRepository.listSize(convert.toEntity(sizeDTO)));
	}
	 
	 @Override
	public List<SizeDTO> findBySizeName(List<String> sizeName) {
		return convert.toSizeDTOs(iSizeRepository.findByNameSizes(sizeName));
	}
	 
	 @Override
	public SizeDTO save(SizeDTO sizeDTO) {
		sizeDTO.setId(null);
		return convert.toDTO(iSizeRepository.save(convert.toEntity(sizeDTO)));
	}
	 
	 @Override
	public SizeDTO update(SizeDTO sizeDTO) {
		EntitySize old = iSizeRepository.findById(sizeDTO.getId()).get();
		EntitySize newSize = convert.toEntity(sizeDTO);
		if(old != null) {
			old = convert.toNewSize(old, newSize);
		}
		return convert.toDTO(iSizeRepository.save(old));
	}
	 
	 @Override
	public Long delete(List<Long> ids) {
		return (long) iSizeRepository.deleteSize(ids);
	}
}
