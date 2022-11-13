package com.clothingstore.controller.APIController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clothingstore.entity.EntityUser;
import com.clothingstore.service.IUserService;

@RestController
public class UserAPI {
	
	@Autowired
	private IUserService iUserService;
	
	@Bean
	private PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
//	@GetMapping("/add-user")
//	public String userAdd() {
//		EntityUser user = new EntityUser();
//		user.setUserName("aaa");
//		user.setPassword(encoder().encode("123456"));
//		iUserService.save(user);
//	return "ok";
//	}
}
