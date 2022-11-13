package com.clothingstore.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.clothingstore.DTO.OrderDTO;
import com.clothingstore.DTO.OrderDTOInput;

public interface IOrderService {
	public OrderDTO save(OrderDTOInput dto);
	public List<OrderDTO> findOrderOfUser();
	public OrderDTO findOrderOfUserAndCode(String code);
	public List<OrderDTO> findAllOrder(Pageable pageable);
	public OrderDTO findOrderByCode(String code);
}
