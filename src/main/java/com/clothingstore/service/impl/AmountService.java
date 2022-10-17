package com.clothingstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothingstore.Convert.ConvertAmount;
import com.clothingstore.DTO.Short.AmountShort;
import com.clothingstore.entity.EntityAmount;
import com.clothingstore.exception.BadRequestException;
import com.clothingstore.repository.IAmountRepository;
import com.clothingstore.service.IAmountService;

@Service
public class AmountService implements IAmountService{
	@Autowired
	private ConvertAmount convertAmount;
	
	@Autowired
	private IAmountRepository amountRepository;
		@Override
		public List<EntityAmount> saveMultiAmounts(List<AmountShort> amounts) {
			try {
				for(int i = 0 ; i < amounts.size() ; i++) {
					for(int j = 0; j < amounts.size() ; j++) {
						if(i!=j) {
							if(amounts.get(i).getColor().getId() == amounts.get(j).getColor().getId()) {
								if(amounts.get(i).getSize().getId() == amounts.get(j).getSize().getId()) {
									throw new BadRequestException("Duplicate Color or Size Information");
								}
							}
						}
					}
				}
				return amountRepository.saveAll(convertAmount.toEntities(amounts));
			}catch (Exception e) {
				throw e;
			}
		}
		
		@Override
		public List<EntityAmount> updateMultiAmount(List<EntityAmount> amount) {
				for(int i = 0 ; i < amount.size() ; i++) {
					for(int j = 0; j < amount.size() ; j++) {
						if(i!=j) {
							if(amount.get(i).getColor().getId() == amount.get(j).getColor().getId()) {
								if(amount.get(i).getSize().getId() == amount.get(j).getSize().getId()) {
									throw new BadRequestException("Duplicate Color or Size Information");
								}
							}
						}
					}
				}
				return amountRepository.saveAll(amount);
		}
		@Override
		public void deleteMultiAmount(List<EntityAmount> amounts) {
			amountRepository.deleteAll(amounts);
			
		}
		
		@Override
		public List<AmountShort> findByIdProductAndIdSizeAndIdColor(Long idproduct, Long idSize, Long idColor) {
			List<EntityAmount> entites = amountRepository.findByIdProductAndIdSizeAndIdColor(idproduct, idSize, idColor);
			List<AmountShort> amountShorts = convertAmount.toAmountShorts(entites);
			return amountShorts;
		}
}
