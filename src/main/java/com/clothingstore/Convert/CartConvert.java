package com.clothingstore.Convert;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clothingstore.DTO.CartDTO;
import com.clothingstore.DTO.CartDetailDTO;
import com.clothingstore.entity.EntityCart;
@Component
public class CartConvert implements abstractConvert<CartDTO, EntityCart> {

	@Autowired
	private CartDetailConvert cartDetailConvert;
	@Override
	public CartDTO toDTO(EntityCart convert) {
		try {
			if(convert != null) {
				CartDTO dto = new CartDTO();
				dto.setcartDetail(cartDetailConvert.toDTO(convert.getCartDetails()));
				dto.setId(convert.getId());
				dto.setCheckAll(convert.getIsCheckAll());
				double totalPrice = 0;
				int totalProduct = 0;
//				if(dto.getcartDetail()!= null && dto.getcartDetail().size() > 0) {
//					for (CartDetailDTO detail : dto.getcartDetail()) {
//						totalPrice += detail.getPrice();
//					}
//					totalProduct= dto.getcartDetail().size();
//				}
//				dto.setTotalPrice(totalPrice);
				dto.setTotalProduct(totalProduct);
			return dto;
			}
			return null;
		}catch (Exception e) {
			throw e;
		}
		
	}

	@Override
	public EntityCart toEntity(CartDTO convert) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityCart newToOld(EntityCart old, EntityCart neww) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CartDTO> toDTO(List<EntityCart> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EntityCart> toEntity(List<CartDTO> DTOs) {
		// TODO Auto-generated method stub
		return null;
	}

}
