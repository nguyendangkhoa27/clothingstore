package com.clothingstore.Convert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.clothingstore.DTO.CartDetailDTO;
import com.clothingstore.entity.EntityCartDetail;
import com.clothingstore.exception.NotFoundException;

@Component
public class CartDetailConvert implements abstractConvert<CartDetailDTO, EntityCartDetail> {

	@Override
	public CartDetailDTO toDTO(EntityCartDetail convert) {
		try {
			if(convert != null) {
				CartDetailDTO cartDetailDTO = new CartDetailDTO();
				cartDetailDTO.setIdCartdetail(convert.getId());
				cartDetailDTO.setIdProduct(convert.getAmount().getProduct().getId());
				cartDetailDTO.setTitleProduct(convert.getAmount().getProduct().getTitle());
				if (convert.getAmount().getProduct().getImg() != null && !convert.getAmount().getProduct().getImg().isEmpty()) {
					String[] img = convert.getAmount().getProduct().getImg().split(",");
					List<String> imgs = new ArrayList<>();
					for (String string : img) {
						imgs.add(string);
					}
					cartDetailDTO.setImgs(imgs);
				}
				cartDetailDTO.setAmountProduct(convert.getAmount().getAmount());
				
				if(convert.getAmount().getSize() != null) {
					cartDetailDTO.setIdSize(convert.getAmount().getSize().getId());
					cartDetailDTO.setNameSize(convert.getAmount().getSize().getNameSize());
				}
				cartDetailDTO.setIdColor(convert.getAmount().getColor().getId());
				cartDetailDTO.setNameColor(convert.getAmount().getColor().getColorName());
				cartDetailDTO.setPrice(convert.getTotalPrice());
				cartDetailDTO.setQuantity(convert.getQuantity());
				cartDetailDTO.setIsActive(convert.getIsActive());
				return	cartDetailDTO;
			}else {
				return null;
			}
		}catch (Exception e) {
			throw e;
		}
	}

	@Override
	public EntityCartDetail toEntity(CartDetailDTO convert) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityCartDetail newToOld(EntityCartDetail old, EntityCartDetail neww) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CartDetailDTO> toDTO(List<EntityCartDetail> entities) {
		try {
			List<CartDetailDTO> cartDetails = new ArrayList<>();
			if(entities!=null && entities.size() > 0) {
				for(EntityCartDetail entity : entities) {
					cartDetails.add(toDTO(entity));
				}
			} 
			cartDetails.sort(Comparator.comparing(CartDetailDTO::getIdCartdetail));
			return cartDetails;
		}catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<EntityCartDetail> toEntity(List<CartDetailDTO> DTOs) {
		// TODO Auto-generated method stub
		return null;
	}

}
