package com.clothingstore.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class EntityUser extends CoreEntity {
	@Column(name="first_name", length=50,nullable = false)
	private String firstName;
	
	@Column(name="last_name", length=50,nullable = false)
	private String lastName;
	
	@Column(name="email", length=150,unique = true,nullable = false)
	private String email;
	
	@Column(name="phone_number", length=20)
	private String phoneNumber;
	
	@Column(name="user_name", length=50,unique = true,nullable = false)
	private String userName;
	
	@Column(name="password", columnDefinition ="TEXT",nullable =false)
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="user_role", joinColumns = @JoinColumn(name="user_id"),
	inverseJoinColumns = @JoinColumn(name="role_id"))
	private List<EntityRole> roles = new ArrayList<>();
	
	@OneToOne(mappedBy = "user")
	private EntityCart cart;
	
	@OneToMany(mappedBy = "user")
	private List<EntityOrder> orders;
	
	@Column(name="address")
	private String address;
	
	@Column(name="user_google_id")
	private String userGoogleId;
	
	@OneToOne(mappedBy = "entityUser")
	private PasswordResetToken passwordResetToken;

	public String getUserGoogleId() {
		return userGoogleId;
	}

	public void setUserGoogleId(String userGoogleId) {
		this.userGoogleId = userGoogleId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<EntityOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<EntityOrder> orders) {
		this.orders = orders;
	}

	public EntityCart getCart() {
		return cart;
	}

	public void setCart(EntityCart cart) {
		this.cart = cart;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<EntityRole> getRoles() {
		return roles;
	}

	public void setRoles(List<EntityRole> roles) {
		this.roles = roles;
	}

	public PasswordResetToken getPasswordResetToken() {
		return passwordResetToken;
	}

	public void setPasswordResetToken(PasswordResetToken passwordResetToken) {
		this.passwordResetToken = passwordResetToken;
	}
	
	
	
}
