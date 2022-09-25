package com.clothingstore.DTO;

import java.util.ArrayList;
import java.util.List;

public class RoleDTO extends AbsDTO {

	private String name;

	private String code;

	private List<MyUser> users = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<MyUser> getUsers() {
		return users;
	}

	public void setUsers(List<MyUser> users) {
		this.users = users;
	}

}
