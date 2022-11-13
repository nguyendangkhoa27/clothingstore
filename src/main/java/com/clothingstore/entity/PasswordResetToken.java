package com.clothingstore.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="pw_reset_token")
public class PasswordResetToken {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private EntityUser entityUser;
	
	@Column(name="token")
	private String token;
	
	@Column(name="expiryDate")
	private Date expiryDate;
	
	public PasswordResetToken() {
	}
	public PasswordResetToken(EntityUser user, String token, Date expiryDate) {
		this.entityUser = user;
		this.token =token;
		this.expiryDate = expiryDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EntityUser getEntityUser() {
		return entityUser;
	}

	public void setEntityUser(EntityUser entityUser) {
		this.entityUser = entityUser;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	
}
