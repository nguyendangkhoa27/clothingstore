package com.clothingstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.clothingstore.Convert.SizeConvert;
import com.clothingstore.DTO.SizeDTO;
import com.clothingstore.DTO.Short.SizeShortDTO;
import com.clothingstore.entity.EntitySize;
import com.clothingstore.exception.BadRequestException;
import com.clothingstore.exception.NotFoundException;
import com.clothingstore.repository.ISizeRepository;
import com.clothingstore.service.ISizeService;

import Message.message;

@Service
public class SizeService implements ISizeService {

	
	@Autowired
	private ISizeRepository iSizeRepository;
	
	@Autowired
	private SizeConvert convert;
	 @Override
	public List<SizeDTO> list(Pageable pageable) {
		return convert.toSizeDTOs(iSizeRepository.findByIsActiveOrderByIdAsc(pageable,true));
	}
	 
	 @Override
	public List<SizeDTO> findBySizeName(List<String> sizeName) {
		return convert.toSizeDTOs(iSizeRepository.findByNameSizes(sizeName));
	}
	 
	 @Override
	public SizeDTO save(SizeDTO sizeDTO) {
		sizeDTO.setId(null);
		try {
			return convert.toDTO(iSizeRepository.save(convert.toEntity(sizeDTO)));
		}catch(Exception e) {
			throw new BadRequestException(message.messageBadRequest);
		}
	}
	 
	 @Override
	public SizeDTO update(SizeDTO sizeDTO) {
		 try {
			EntitySize old = iSizeRepository.findById(sizeDTO.getId()).get();
			EntitySize newSize = convert.toEntity(sizeDTO);
			if(old != null) {
				old = convert.toNewSize(old, newSize);
			}
			return convert.toDTO(iSizeRepository.save(old));
		 }catch (Exception e) {
			throw new BadRequestException(message.messageBadRequest);
		}
		 }
	 
	 @Override
	public Long delete(List<Long> ids) {
		 int i =  iSizeRepository.deleteSize(ids);
			if(i > 0) {
				return (long) i;
			}
			throw new NotFoundException(message.messageSizeIsNotFound);
		
	}
	 
	 @Override
	public List<EntitySize> findSizeByIds(List<SizeShortDTO> sizeShorts) {
		List<Long> ids = new ArrayList<>(); 
		 for (SizeShortDTO sizeShortDTO : sizeShorts) {
			ids.add(sizeShortDTO.getId());
		}
		return iSizeRepository.FindSizeByIds(ids);
	}
	 @Override
		public SizeDTO findOne(Long id) {
				try {
					SizeDTO dto = convert.toDTO(iSizeRepository.findById(id).get());
					if(dto !=null ) {
						return dto;
					}
					throw new NotFoundException(message.messageSizeIsNotFound);
				}catch(Exception e) {
					throw new BadRequestException(message.messageBadRequest);
				}
				
			}
}
