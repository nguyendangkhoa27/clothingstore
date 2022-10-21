package com.clothingstore.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clothingstore.entity.EntitySize;

@Transactional
public interface ISizeRepository extends JpaRepository<EntitySize, Long> {
	@Modifying
	@Query(value="SELECT * FROM size WHERE is_active = true AND name_size IN (:names)",nativeQuery = true)
	List<EntitySize> findByNameSizes(@Param("names") List<String> nameSizes);
	
	@Modifying
	@Query(value="UPDATE size SET is_active = false WHERE id IN (:ids)",nativeQuery = true)
	int deleteSize(@Param("ids") List<Long> ids);
	
	@Query(value="SELECT * FROM size WHERE is_active = true AND id IN (:ids)",nativeQuery = true)
	List<EntitySize> FindSizeByIds(@Param("ids") List<Long> ids);
	
	List<EntitySize> findByIsActiveOrderByIdAsc(Pageable pageable ,boolean isActive);
	
	Long countByIsActive(boolean isActive);
}
