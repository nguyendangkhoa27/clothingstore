package com.clothingstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clothingstore.entity.EntityUser;

public interface IUserRepository extends JpaRepository<EntityUser, Long> {
		List<EntityUser> findByUserName(String userName);
		Long countByIsActive(boolean isActive);
}
