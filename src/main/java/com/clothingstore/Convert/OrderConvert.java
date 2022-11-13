package com.clothingstore.Convert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clothingstore.DTO.OrderDTO;
import com.clothingstore.DTO.OrderDTOInput;
import com.clothingstore.entity.EntityOrder;

@Component
public class OrderConvert implements abstractConvert<OrderDTO, EntityOrder> {

	private Random random = new Random();
	@Autowired
	private OrderDetailConvert orderDetailConvert;

	@Override
	public OrderDTO toDTO(EntityOrder convert) {
		try {
			if (convert != null) {
				OrderDTO orderDTO = new OrderDTO();
				orderDTO.setAddress(convert.getAddress());
				orderDTO.setCode(convert.getCode());
				orderDTO.setDetails(orderDetailConvert.toDTO(convert.getDetails()));
				orderDTO.setEmail(convert.getEmail());
				orderDTO.setFirstName(convert.getFirstName());
				orderDTO.setLastName(convert.getLastName());
				orderDTO.setIsOnline(convert.getIsOnline());
				orderDTO.setOrderDate(convert.getOrderDate());
				orderDTO.setPhoneNumber(convert.getPhoneNumber());
				orderDTO.setTotalPriceOrder(convert.getTotalPriceOrder());
				return orderDTO;
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public EntityOrder toEntity(OrderDTO convert) {
		try {
			if (convert != null) {
				EntityOrder orderEntity = new EntityOrder();
				orderEntity.setAddress(convert.getAddress());
				String Code = randomCode();
				orderEntity.setCode(randomCode());
				orderEntity.setEmail(convert.getEmail());
				orderEntity.setFirstName(convert.getFirstName());
				orderEntity.setLastName(convert.getLastName());
				orderEntity.setIsOnline(convert.getIsOnline());
				orderEntity.setOrderDate(new Date());
				orderEntity.setPhoneNumber(convert.getPhoneNumber());
				orderEntity.setTotalPriceOrder(convert.getTotalPriceOrder());
				return orderEntity;
			}
			return null;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public EntityOrder newToOld(EntityOrder old, EntityOrder neww) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDTO> toDTO(List<EntityOrder> entities) {
		try {
			if (entities != null && entities.size() > 0) {
				List<OrderDTO> dtos = new ArrayList<>();
				for (EntityOrder entityOrder : entities) {
					dtos.add(toDTO(entityOrder));
				}
				return dtos;
			} else {
				return new ArrayList<>();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@Override
	public List<EntityOrder> toEntity(List<OrderDTO> DTOs) {
		return null;
	}

	private String randomCode() {
		final String alpha = "qwertyuioplkjhgfdsazxcvbnm";
		final String alphaUpperCase = alpha.toUpperCase();
		final String number = "0123456789";
		final String alphaAndUpperCase = alpha+alphaUpperCase;
//		final String all = alpha + alphaUpperCase + number;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			int numberRandom = randomNumber(0, alphaAndUpperCase.length() - 1);
			char ch = alphaAndUpperCase.charAt(numberRandom);
			sb.append(ch);
		}
		for(int i = 0; i< 5 ; i++) {
			int numberRandom = randomNumber(0, number.length() - 1);
			char ch = number.charAt(numberRandom);
			sb.append(ch);
		}
		return sb.toString();
	}

	private int randomNumber(int min, int max) {
		return random.nextInt(max) + min;
	}
	
	public EntityOrder inputToEntity(OrderDTOInput convert) {
		try {
			if (convert != null) {
				EntityOrder orderEntity = new EntityOrder();
				orderEntity.setAddress(convert.getAddress());
				String Code = randomCode();
				orderEntity.setCode(randomCode());
				orderEntity.setEmail(convert.getEmail());
				orderEntity.setFirstName(convert.getFirstName());
				orderEntity.setLastName(convert.getLastName());
				orderEntity.setIsOnline(convert.getIsOnline());
				orderEntity.setOrderDate(new Date());
				orderEntity.setPhoneNumber(convert.getPhoneNumber());
				orderEntity.setTotalPriceOrder(convert.getTotalPriceOrder());
				return orderEntity;
			}
			return null;
		} catch (Exception e) {
			throw e;
		}
	}
}
