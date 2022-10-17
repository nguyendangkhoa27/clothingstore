package com.clothingstore.service;

import java.util.List;

import com.clothingstore.DTO.Short.AmountShort;
import com.clothingstore.entity.EntityAmount;

public interface IAmountService {
	List<EntityAmount> saveMultiAmounts(List<AmountShort> amount);
	
	List<EntityAmount> updateMultiAmount(List<EntityAmount> amount);
	
	void deleteMultiAmount(List<EntityAmount> amounts);
	
	public List<AmountShort> findByIdProductAndIdSizeAndIdColor(Long idproduct,Long idSize,Long idColor);
}
