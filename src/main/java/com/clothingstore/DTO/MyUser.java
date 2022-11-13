package com.clothingstore.DTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MyUser extends User {

	private static final long serialVersionUID = 1L;

	public MyUser(String userName,String password,boolean enabled,boolean accountNonExpired,boolean creadentialsNonExpired,
			boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		super(userName, password, enabled, accountNonExpired, creadentialsNonExpired, accountNonLocked, authorities);
	}
	public MyUser(String userName, String password, Collection<GrantedAuthority> author) {
		super(userName, password, author);
	}
	
	private Long id;
	
	private String firstName;

	private String lastName;
	
	private String fullName;

	private String email;

	private String phoneNumber;
	
	private Boolean isActive;
	
	private CartDTO cartDTO;
	
	
	public CartDTO getCartDTO() {
		return cartDTO;
	}
	public void setCartDTO(CartDTO cartDTO) {
		this.cartDTO = cartDTO;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	private List<RoleDTO> roles = new ArrayList<>();

	public String getFirstName() {
		return firstName;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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

	public List<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
