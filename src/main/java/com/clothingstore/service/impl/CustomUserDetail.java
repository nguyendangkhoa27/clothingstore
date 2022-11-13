package com.clothingstore.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.clothingstore.Convert.CartConvert;
import com.clothingstore.DTO.MyUser;
import com.clothingstore.entity.EntityCart;
import com.clothingstore.entity.EntityRole;
import com.clothingstore.entity.EntityUser;
import com.clothingstore.repository.ICartRepository;
import com.clothingstore.repository.IUserRepository;

@Service
@Transactional
public class CustomUserDetail implements UserDetailsService {

	@Autowired
	private IUserRepository iUserRepository;
	
	@Autowired
	private CartConvert cartConvert;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		EntityUser entityUser;
		if(!username.contains("@")) {
		 entityUser = iUserRepository.findOneByUserNameAndIsActive(username, true);
		}else {
			entityUser = iUserRepository.findOneByEmailAndIsActive(username, true);
		}
		 if (entityUser == null) {
			throw new UsernameNotFoundException("User not found");
		}
	   Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (EntityRole role : entityUser.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getCode()));
		}
		MyUser myUser = new MyUser(entityUser.getEmail(), entityUser.getPassword(),true,true,true,true,
				authorities);	
				myUser.setId(entityUser.getId());
		return myUser;
	}
}
