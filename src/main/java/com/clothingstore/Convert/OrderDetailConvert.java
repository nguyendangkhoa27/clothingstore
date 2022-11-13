package com.clothingstore.Convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clothingstore.DTO.OrderDetailDTO;
import com.clothingstore.entity.EntityCartDetail;
import com.clothingstore.entity.EntityOrder;
import com.clothingstore.entity.EntityOrderDetail;
import com.clothingstore.repository.IAmountRepository;
@Component
public class OrderDetailConvert implements abstractConvert<OrderDetailDTO, EntityOrderDetail> {
	@Autowired
	private IAmountRepository amountRepository;
	@Override
	public OrderDetailDTO toDTO(EntityOrderDetail convert) {
		try {
			OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
			if(convert != null) {
				orderDetailDTO.setIdOrderDetail(convert.getId());
				orderDetailDTO.setIdProduct(convert.getAmount().getProduct().getId());
				orderDetailDTO.setTitleProduct(convert.getAmount().getProduct().getTitle());
				if (convert.getAmount().getProduct().getImg() != null && !convert.getAmount().getProduct().getImg().isEmpty()) {
					String[] img = convert.getAmount().getProduct().getImg().split(",");
					List<String> imgs = new ArrayList<>();
					for (String string : img) {
						imgs.add(string);
					}
					orderDetailDTO.setImgs(imgs);
				}
				if(convert.getAmount().getSize() != null) {
					orderDetailDTO.setIdSize(convert.getAmount().getSize().getId());
					orderDetailDTO.setNameSize(convert.getAmount().getSize().getNameSize());
				}
				orderDetailDTO.setIdColor(convert.getAmount().getColor().getId());
				orderDetailDTO.setNameColor(convert.getAmount().getColor().getColorName());
				orderDetailDTO.setPrice(convert.getTotalPrice());
				orderDetailDTO.setQuantity(convert.getQuantity());
				return	orderDetailDTO;
			}else {
				return orderDetailDTO;
			}
		}catch (Exception e) {
			throw e;
		}
		
	}

	@Override
	public EntityOrderDetail toEntity(OrderDetailDTO convert) {
		try {
			EntityOrderDetail orderDetail = new EntityOrderDetail();
			orderDetail.setAmount(amountRepository.findByIdProductAndIdSizeAndIdColor(convert.getIdProduct(), convert.getIdSize(), convert.getIdColor()).get(0));
			orderDetail.setIsActive(true);
			orderDetail.setQuantity(convert.getQuantity());
			orderDetail.setTotalPrice(convert.getPrice());
			return orderDetail;
		}catch (Exception e) {
			return null;
		}
		
	}
	public EntityOrderDetail toEntityAddOrderCode(EntityCartDetail convert, EntityOrder order) {
		try {
			EntityOrderDetail orderDetail = new EntityOrderDetail();
			orderDetail.setAmount(convert.getAmount());
			orderDetail.setIsActive(true);
			orderDetail.setQuantity(convert.getQuantity());
			orderDetail.setTotalPrice(convert.getTotalPrice());
			orderDetail.setOrders(order);
			return orderDetail;
		}catch (Exception e) {
			return null;
		}
	}
	@Override
	public EntityOrderDetail newToOld(EntityOrderDetail old, EntityOrderDetail neww) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDetailDTO> toDTO(List<EntityOrderDetail> entities) {
		try {
			List<OrderDetailDTO> orderDetails = new ArrayList<>();
			if(entities!=null && entities.size() > 0) {
				for(EntityOrderDetail entity : entities) {
					orderDetails.add(toDTO(entity));
				}
				return orderDetails;
			}
			return null;
		}catch (Exception e) {
			throw e;
		}
		
	}

	@Override
	public List<EntityOrderDetail> toEntity(List<OrderDetailDTO> DTOs) {
		try {
			List<EntityOrderDetail> entities = new ArrayList<>();
			for(OrderDetailDTO orderDetailDTO : DTOs) {
				entities.add(toEntity(orderDetailDTO));
			}
			return entities;
		}catch (Exception e) {
			return null;
		}
	}
//	public List<EntityOrderDetail> toEntityAddOrderCode(List<OrderDetailDTO> DTOs,EntityOrder entityOrder) {
//		try {
//			List<EntityOrderDetail> entities = new ArrayList<>();
//			for(OrderDetailDTO orderDetailDTO : DTOs) {
//				entities.add(toEntityAddOrderCode(orderDetailDTO,entityOrder));
//			}
//			return entities;
//		}catch (Exception e) {
//			return null;
//		}
//	}

}
