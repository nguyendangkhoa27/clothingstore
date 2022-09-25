package com.clothingstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clothingstore.entity.EntityRole;

public interface IRoleRepository extends JpaRepository<EntityRole, Long> {
}
