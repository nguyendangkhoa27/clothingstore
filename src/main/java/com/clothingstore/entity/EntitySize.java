package com.clothingstore.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="size")
public class EntitySize extends CoreEntity {
	@Column(name="name_size")
	private String nameSize;
	
	@ManyToMany(mappedBy = "sizes")
	private Set<EntityProduct> products;

	public String getNameSize() {
		return nameSize;
	}

	public void setNameSize(String nameSize) {
		this.nameSize = nameSize;
	}

	public Set<EntityProduct> getProducts() {
		return products;
	}

	public void setProducts(Set<EntityProduct> products) {
		this.products = products;
	}
	
}
