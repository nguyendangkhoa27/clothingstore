package com.clothingstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clothingstore.entity.EntityUser;

public interface IUserRepository extends JpaRepository<EntityUser, Long> {
		List<EntityUser> findByUserName(String userName);
		Long countByIsActive(boolean isActive);
		EntityUser findOneByUserNameAndIsActive(String userName,boolean isActive);
		EntityUser findOneByEmailAndIsActive(String email,boolean isActive);
		EntityUser findOneByUserName(String userName);
		EntityUser findOneByEmail(String email);
		EntityUser findOneByEmailAndUserGoogleIdAndIsActive(String email,String userGoogleId,boolean isActive);
}
