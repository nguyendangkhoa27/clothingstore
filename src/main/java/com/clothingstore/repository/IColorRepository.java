package com.clothingstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clothingstore.entity.EntityColor;


public interface IColorRepository extends JpaRepository<EntityColor, Long> {

}
