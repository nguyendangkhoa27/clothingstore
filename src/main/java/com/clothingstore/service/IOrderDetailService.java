package com.clothingstore.service;

import java.util.List;

import com.clothingstore.entity.EntityOrderDetail;

public interface IOrderDetailService {
	public List<EntityOrderDetail> saveOrderDetail(List<EntityOrderDetail> detail);
}
