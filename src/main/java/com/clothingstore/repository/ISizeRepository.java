package com.clothingstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clothingstore.entity.EntitySize;


public interface ISizeRepository extends JpaRepository<EntitySize, Long> {

}
