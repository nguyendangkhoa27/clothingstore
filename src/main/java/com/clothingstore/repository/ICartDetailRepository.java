package com.clothingstore.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clothingstore.entity.EntityCartDetail;

@Transactional
public interface ICartDetailRepository extends JpaRepository<EntityCartDetail,Long> {
	
	@Modifying
	@Query(value="select c.* from cart_detail c Where c.cart_id = :cart_id",nativeQuery = true)
	public List<EntityCartDetail> findByIdCart(@Param("cart_id") Long cartId);
	
	@Modifying
	@Query(value="delete from cart_detail c WHERE c.cart_id = :user_id AND c.id IN(:id_cart_details)",nativeQuery = true)
	public int deleteById(@Param("user_id") Long userId,@Param("id_cart_details") List<Long> idCartDetails);
	
	@Query(value="select * from cart_detail c where c.cart_id = :user_id AND c.id = :id_cart_detail",nativeQuery = true)
	public EntityCartDetail findByIdCartDetailAndUser(@Param("user_id") Long userId,@Param("id_cart_detail") Long idCartDetail);
	
	@Modifying
	@Query(value="update cart_detail set is_active = :is_active where cart_id = :user_id",nativeQuery =  true)
	public int updateIsActiveByUserId(@Param("user_id") Long userId,@Param("is_active") Boolean isActive);
}
