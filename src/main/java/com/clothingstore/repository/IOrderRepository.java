package com.clothingstore.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clothingstore.entity.EntityOrder;

@Transactional
public interface IOrderRepository extends JpaRepository<EntityOrder, Long> {
	
	@Query(value = "select * from orders where code = :code AND user_id = :user_id",nativeQuery = true)
	public EntityOrder findByUserAndCode(@Param("user_id") Long userId, @Param("code") String code);
	
	EntityOrder findOneByCode(String code);
	
	Page<EntityOrder> findAll(Pageable pageable);
	
	
}
