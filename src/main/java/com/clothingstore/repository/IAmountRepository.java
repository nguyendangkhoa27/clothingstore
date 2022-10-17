package com.clothingstore.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clothingstore.entity.EntityAmount;

@Transactional
public interface IAmountRepository extends JpaRepository<EntityAmount, Long> {

	@Query(value="select * from amount where product_id = :productId AND size_id = :sizeId AND color_id = :colorId",nativeQuery = true)
 	public List<EntityAmount> findByIdProductAndIdSizeAndIdColor(@Param("productId") Long idproduct, @Param("sizeId") Long idSize, @Param("colorId") Long idColor);
}
