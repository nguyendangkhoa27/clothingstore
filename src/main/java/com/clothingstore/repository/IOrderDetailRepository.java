package com.clothingstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clothingstore.entity.EntityOrderDetail;

public interface IOrderDetailRepository extends JpaRepository<EntityOrderDetail, Long>{

}
