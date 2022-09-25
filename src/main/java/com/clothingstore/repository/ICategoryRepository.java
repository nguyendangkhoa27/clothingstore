package com.clothingstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clothingstore.entity.EntityCategory;

public interface ICategoryRepository extends JpaRepository<EntityCategory, Long> {

}
