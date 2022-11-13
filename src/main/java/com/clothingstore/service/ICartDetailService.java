package com.clothingstore.service;

import java.util.List;

import com.clothingstore.DTO.CartDTO;
import com.clothingstore.DTO.CartDetailDTO;
import com.clothingstore.DTO.Short.CartDetailShort;
import com.clothingstore.entity.EntityCart;
import com.clothingstore.entity.EntityCartDetail;

public interface ICartDetailService {
	public CartDetailDTO saveCartDetail(EntityCartDetail cartDetailEntity, CartDetailDTO cartDetailDTO,EntityCart cart); 
	public CartDTO updateCartDetail(CartDetailShort cartDetailShort);
	public CartDTO deleteCartDetail(List<Long> idCartDetails);
	public CartDTO updateIsActive(Long id);
	public int updateSelectALLIsActive(Long userId, boolean isActive);
	
}
