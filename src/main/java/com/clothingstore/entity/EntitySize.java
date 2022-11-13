package com.clothingstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="size")
public class EntitySize extends CoreEntity {
	@Column(name="name_size",nullable = false, unique = true)
	private String nameSize;
	
	@OneToMany(mappedBy = "size")
	private java.util.List<EntityAmount> amount;
	
	public String getNameSize() {
		return nameSize;
	}

	public void setNameSize(String nameSize) {
		this.nameSize = nameSize;
	}

	
}
