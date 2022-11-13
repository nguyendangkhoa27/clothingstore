package com.clothingstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothingstore.Convert.OrderDetailConvert;
import com.clothingstore.entity.EntityAmount;
import com.clothingstore.entity.EntityOrderDetail;
import com.clothingstore.exception.QuantityCartDetailException;
import com.clothingstore.repository.IAmountRepository;
import com.clothingstore.repository.IOrderDetailRepository;
import com.clothingstore.repository.IOrderRepository;
import com.clothingstore.service.IOrderDetailService;

@Service
public class OrderDetailService implements IOrderDetailService {
	@Autowired
	private IOrderDetailRepository iOrderDetailRepository;
	
	@Autowired
	private OrderDetailConvert convert;
	
	@Autowired
	private IOrderRepository iOrderRepository;
	@Autowired
	private IAmountRepository amountRepository;
	@Override
	public List<EntityOrderDetail> saveOrderDetail(List<EntityOrderDetail> detail) {
		List<EntityAmount> amounts = new ArrayList<>();
		for(EntityOrderDetail orderDetail : detail) {
			EntityAmount amount = orderDetail.getAmount();
			if(amount.getAmount() < orderDetail.getQuantity()) {
				iOrderRepository.delete(orderDetail.getOrders());
				throw new QuantityCartDetailException("Incorrect Quantity !!!");
			}else {
				amount.setAmount(amount.getAmount()-orderDetail.getQuantity());
				amounts.add(amount);
			}
		}
		detail = iOrderDetailRepository.saveAll(detail);
		amountRepository.saveAll(amounts);
		return detail;
	}
	
	
}
