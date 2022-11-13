package com.clothingstore.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class EntityCategory extends CoreEntity {

	@Column(name="category_slug", length = 150,unique=true,nullable=false)
	private String categorySlug;
	
	@Column(name="image",columnDefinition = "TEXT")
	private String img;
	
	@OneToMany(mappedBy = "category")
	private List<EntityProduct> products;

	public String getCategorySlug() {
		return categorySlug;
	}

	public void setCategorySlug(String categorySlug) {
		this.categorySlug = categorySlug;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public List<EntityProduct> getProducts() {
		return products;
	}

	public void setProducts(List<EntityProduct> products) {
		this.products = products;
	}
	
}
