package com.clothingstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clothingstore.entity.PasswordResetToken;

public interface IPasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
	public List<PasswordResetToken> findByToken(String token);
}
