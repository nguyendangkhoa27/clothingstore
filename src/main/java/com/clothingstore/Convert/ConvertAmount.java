package com.clothingstore.Convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clothingstore.DTO.Short.AmountShort;
import com.clothingstore.DTO.Short.ColorShortDTO;
import com.clothingstore.DTO.Short.SizeShortDTO;
import com.clothingstore.entity.EntityAmount;
import com.clothingstore.entity.EntityColor;
import com.clothingstore.entity.EntitySize;
import com.clothingstore.exception.NotFoundException;
import com.clothingstore.service.IColorService;
import com.clothingstore.service.ISizeService;

@Component
public class ConvertAmount {
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private ISizeService sizeService;
	
	@Autowired
	private ColorConvert colorConvert;
	
	@Autowired
	private SizeConvert sizeConvert;
	public AmountShort toAmountShort(EntityAmount amount) {
		if(amount!=null) {
			AmountShort amountShort = new AmountShort(); 
			amountShort.setAmount(amount.getAmount());
			if(amount.getColor() != null) {
				amountShort.setColor(new ColorShortDTO(amount.getColor().getId(), amount.getColor().getColorName()));	
			}else {
				amountShort.setColor(null);
			}
			if(amount.getSize() != null) {
				amountShort.setSize(new SizeShortDTO(amount.getSize().getId(), amount.getSize().getNameSize()));	
			}
			return amountShort;
		}
		throw new NotFoundException("sản phẩm không có màu hoặc size này");
	}
	
	public EntityAmount toEntity(AmountShort amountshort) {
		if(amountshort!=null) {
			EntityAmount amountEntity = new EntityAmount();
			amountEntity.setAmount(amountshort.getAmount());
			if(amountshort.getColor() != null && amountshort.getColor().getId() > 0) {
				EntityColor color = colorConvert.toEntity(colorService.findOne(amountshort.getColor().getId()));
				amountEntity.setColor(color);
			}else {
				amountEntity.setColor(null);
			}
			if(amountshort.getSize()!=null && amountshort.getSize().getId() > 0) {
				EntitySize size = sizeConvert.toEntity(sizeService.findOne(amountshort.getSize().getId()));;
				amountEntity.setSize(size);
			}else {
				amountEntity.setSize(null);
			}
			return amountEntity;
		}
		throw new NotFoundException("sản phẩm không có màu hoặc size này");
	}
	
	public List<EntityAmount> toEntities(List<AmountShort> amountShorts){
		if(amountShorts != null && amountShorts.size() > 0) {
			List<EntityAmount> entities = new ArrayList<>();
			for (AmountShort amountShort : amountShorts) {
				entities.add(toEntity(amountShort));
			}
			return entities;
		}
		throw new NotFoundException("Có một không có màu hoặc size nào đó");
	}
	
	public List<AmountShort> toAmountShorts(List<EntityAmount> entityAmounts){
		if(entityAmounts != null && entityAmounts.size() > 0) {
			List<AmountShort> shorts = new ArrayList<>();
			for (EntityAmount entityAmount : entityAmounts) {
				shorts.add(toAmountShort(entityAmount));
			}
			entityAmounts = null;
			return shorts;
		}
		throw new NotFoundException("Có một không có màu hoặc size nào đó");
	}
}
