package com.clothingstore.DTO;

import java.util.List;

import com.clothingstore.DTO.Short.RoleShort;

public class UserDTO {
	private Long id;
	
	private String userName;
	
	private String password;
	
	private List<RoleShort> role ;
	
	private String firstName;

	private String lastName;

	private String fullName;

	private String email;

	private String phoneNumber;

	private Boolean isActive;
	
	private CartDTO cartDTO;
	
	private String UserGoogleId;
	

	
	public CartDTO getCartDTO() {
		return cartDTO;
	}
	public void setCartDTO(CartDTO cartDTO) {
		this.cartDTO = cartDTO;
	}
	public String getUserGoogleId() {
		return UserGoogleId;
	}
	public void setUserGoogleId(String userGoogleId) {
		UserGoogleId = userGoogleId;
	}
	public UserDTO() {}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<RoleShort> getRole() {
		return role;
	}

	public void setRole(List<RoleShort> role) {
		this.role = role;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	
}
