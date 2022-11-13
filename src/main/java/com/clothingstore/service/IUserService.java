package com.clothingstore.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.clothingstore.DTO.UserDTO;
import com.clothingstore.DTO.Short.AccountGoogle;
import com.clothingstore.DTO.Short.ChangePassword;
import com.clothingstore.entity.EntityUser;

public interface IUserService {
	public UserDTO save(UserDTO myUser);
	public UserDTO findUserByUserNameOrEmail(String userNameOrEmail);
	public List<UserDTO> findAll(Pageable pageable); 
	public UserDTO update(UserDTO user);
	public UserDTO findUserByEmailAndUId(String email, String uId);
	public String loginGG(AccountGoogle accountGoogle);
	public Integer count();
	public UserDTO resetPassword(EntityUser entityUser, String newPassword);
	public UserDTO changePassword(ChangePassword changePassword);
}
