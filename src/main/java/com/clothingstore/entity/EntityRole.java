package com.clothingstore.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class EntityRole extends CoreEntity {
	
	@Column(name="name",length = 20)
	private String name;
	
	@Column(name="code",length = 20,unique =true,nullable=false)
	private String code;
	
	@ManyToMany(mappedBy = "roles")
	private List<EntityUser> users = new ArrayList<>();

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

	public List<EntityUser> getUsers() {
		return users;
	}

	public void setUsers(List<EntityUser> users) {
		this.users = users;
	}
	
	
}
