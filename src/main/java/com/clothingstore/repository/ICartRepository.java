package com.clothingstore.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clothingstore.entity.EntityCart;

@Transactional
public interface ICartRepository extends JpaRepository<EntityCart,Long> {

	@Modifying
	@Query(value="update cart set is_check_all = false where user_id = :cart_id",nativeQuery = true)
	public int updateIsActiveIfListCartDetailNull(@Param("cart_id") Long cartId); 
}
