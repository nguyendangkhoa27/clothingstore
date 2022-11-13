package com.clothingstore.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothingstore.DTO.UserDTO;
import com.clothingstore.DTO.Short.passwordReset;
import com.clothingstore.entity.EntityUser;
import com.clothingstore.entity.PasswordResetToken;
import com.clothingstore.exception.BadRequestException;
import com.clothingstore.exception.NotFoundException;
import com.clothingstore.repository.IPasswordResetTokenRepository;
import com.clothingstore.repository.IUserRepository;
import com.clothingstore.service.IPasswordResetTokenService;
import com.clothingstore.service.IUserService;
import com.clothingstore.util.GenerateRandomString;

@Service
public class PasswordResetTokenService implements IPasswordResetTokenService {

	
	@Autowired
	private IUserRepository iUserRepository;
	
	@Autowired
	private IUserService iUserService;
	
	@Autowired
	private IPasswordResetTokenRepository iPasswordResetTokenRepository;
	
	@Override
	public PasswordResetToken genarateTokenResetPassword(String email) {
		String token;
		EntityUser user = iUserRepository.findOneByEmail(email);//kiểm tra email có tồn tại trong db
		PasswordResetToken passwordResetToken;
		if(user != null) {
			if(user.getUserGoogleId()!=null) {
				throw new BadRequestException("this account implements google login method please contact google to get the account back!");
			}
			token = GenerateRandomString.randomCode(6);
			passwordResetToken = user.getPasswordResetToken();
			if(passwordResetToken != null) {
				if(!checkExpiryToken(passwordResetToken)) {
					passwordResetToken.setExpiryDate(new Date());
					passwordResetToken.setToken(token);
				}else {
					throw new BadRequestException("Please resend your request in a few minutes!");
				}
			}else {
				passwordResetToken = new PasswordResetToken(user, token, new Date());
				
			}
			passwordResetToken = iPasswordResetTokenRepository.save(passwordResetToken);
			return passwordResetToken;
			
		}
		throw new NotFoundException("Not found account in system!!!");
	}

	@Override
	public PasswordResetToken checkValidToken(String token) {
		List<PasswordResetToken> list = iPasswordResetTokenRepository.findByToken(token);
		PasswordResetToken resetToken = list != null && list.size() > 0 ? list.get(0) : null;
		if(resetToken != null) {
			if (checkExpiryToken(resetToken)) {
				return resetToken;
			}else {
				throw new BadRequestException("token has expired!");
			}
		}
		throw new BadRequestException("token incorrect!!!");
	}
	
	private boolean checkExpiryToken(PasswordResetToken passwordResetToken) {
		Date expiryDate =  passwordResetToken.getExpiryDate();
		Date currentTime = new Date(System.currentTimeMillis());
		boolean check = (expiryDate.getTime() + 1000 * 60 * 5) - currentTime.getTime() >= 0 ? true : false;
		return check;
	}
	
	@Override
	public UserDTO updatePassword(passwordReset passwordReset) {
		PasswordResetToken passwordResetToken = checkValidToken(passwordReset.getToken());
		if(passwordResetToken != null) {
				UserDTO dto = iUserService.resetPassword(passwordResetToken.getEntityUser(), passwordReset.getPassword());
				iPasswordResetTokenRepository.delete(passwordResetToken);
				return dto;
		}else {
			throw new BadRequestException("token invalid!");
		}
		
	}
	
	

}
