package com.clothingstore.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.clothingstore.Convert.OrderConvert;
import com.clothingstore.Convert.OrderDetailConvert;
import com.clothingstore.DTO.MyUser;
import com.clothingstore.DTO.OrderDTO;
import com.clothingstore.DTO.OrderDTOInput;
import com.clothingstore.entity.EntityCartDetail;
import com.clothingstore.entity.EntityOrder;
import com.clothingstore.entity.EntityOrderDetail;
import com.clothingstore.entity.EntityUser;
import com.clothingstore.repository.IOrderRepository;
import com.clothingstore.repository.IUserRepository;
import com.clothingstore.service.ICartDetailService;
import com.clothingstore.service.IOrderDetailService;
import com.clothingstore.service.IOrderService;

@Service
@Transactional
public class OrderService implements IOrderService {
	@Autowired
	private IOrderRepository iOrderRepository;
	
	@Autowired
	private IOrderDetailService iOrderDetailService;
	
	@Autowired
	private IUserRepository iUserRepository;
	@Autowired
	private OrderConvert convert;
	
	@Autowired
	private OrderDetailConvert orderDetailConvert;
	
	@Autowired
	private ICartDetailService cartDetailService;
	@Override
	public OrderDTO save(OrderDTOInput dto) {
		EntityOrder entityOrder = convert.inputToEntity(dto);
		MyUser myUser = (MyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EntityUser user = iUserRepository.findOneByEmail(myUser.getUsername());
		entityOrder.setUser(user);
		entityOrder = iOrderRepository.save(entityOrder);
		List<EntityCartDetail> cartDetails = user.getCart().getCartDetails();
		List<EntityOrderDetail> orderDetail = new ArrayList<>();
		for(EntityCartDetail cartDetail : cartDetails) {
			for (Long idCartDetail : dto.getIdCartDetail()) {
				if(cartDetail.getId() == idCartDetail) {
					orderDetail.add(orderDetailConvert.toEntityAddOrderCode(cartDetail,entityOrder));
				}
			}
		}
		entityOrder.setDetails(iOrderDetailService.saveOrderDetail(orderDetail));
		cartDetailService.deleteCartDetail(dto.getIdCartDetail());
		return convert.toDTO(entityOrder);
	}
	
	@Override
	public List<OrderDTO> findOrderOfUser() {
		MyUser myUser = (MyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EntityUser user = iUserRepository.findOneByEmail(myUser.getUsername());
		List<OrderDTO> orderDTOs = convert.toDTO(user.getOrders());
		if(orderDTOs != null && orderDTOs.size() > 0) {
		Collections.sort(orderDTOs, new Comparator<OrderDTO>() {
			@Override
			public int compare(OrderDTO o1, OrderDTO o2) {
				return o2.getOrderDate().compareTo(o1.getOrderDate());
			}
		});
		}else {
			orderDTOs = new ArrayList<>();
		}
		return orderDTOs;
	}
	
	@Override
	public OrderDTO findOrderOfUserAndCode(String code) {
		MyUser myUser = (MyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EntityUser user = iUserRepository.findOneByEmail(myUser.getUsername());
		OrderDTO dto = convert.toDTO(iOrderRepository.findByUserAndCode(user.getId(), code));
		return dto;
	}
	
	@Override
	public List<OrderDTO> findAllOrder(Pageable pageable) {
		List<OrderDTO> dtos = convert.toDTO(iOrderRepository.findAll(pageable).getContent());
		return dtos;
	}
	
	@Override
	public OrderDTO findOrderByCode(String code) {
		return convert.toDTO(iOrderRepository.findOneByCode(code));
	}
}
