package com.clothingstore.DTO.Short;

public class AccountGoogle {
	private String uId;
	private String email;
	private String displayName;
	
	public AccountGoogle() {}
	
	public AccountGoogle(String uId, String email, String displayName) {
		this.uId = uId;
		this.email = email;
		this.displayName = displayName;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	
}
