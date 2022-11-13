package com.clothingstore.service;

import com.clothingstore.entity.PasswordResetToken;

public interface IMailService {
	public void sendMailResetPassword(PasswordResetToken passwordResetToken);
}
