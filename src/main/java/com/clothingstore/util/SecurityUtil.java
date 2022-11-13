package com.clothingstore.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.clothingstore.DTO.MyUser;
import com.clothingstore.entity.EntityUser;
import com.clothingstore.repository.IUserRepository;

public class SecurityUtil {
	
	public static EntityUser getUserByContext(IUserRepository userRepository) {
		try {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if(authentication != null) {
				MyUser myUser =(MyUser)authentication.getPrincipal();
				EntityUser entityUser = userRepository.findOneByEmail(myUser.getUsername());
				return entityUser;
			}else {
				throw new AuthenticationException("You are not logged in!!!") {};
			}
		}catch (Exception e) {
			throw e;
		}
		
	}
}
