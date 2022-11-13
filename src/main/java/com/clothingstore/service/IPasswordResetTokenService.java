package com.clothingstore.service;

import com.clothingstore.DTO.UserDTO;
import com.clothingstore.DTO.Short.passwordReset;
import com.clothingstore.entity.PasswordResetToken;

public interface IPasswordResetTokenService {
	public PasswordResetToken genarateTokenResetPassword(String email);
	public PasswordResetToken checkValidToken(String token);
	public UserDTO updatePassword(passwordReset passwordReset);
}
