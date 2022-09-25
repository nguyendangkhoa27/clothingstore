package com.clothingstore.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class EntityProduct extends CoreEntity {

	@Column(name="title",length=150)
	private String title;
	
	@Column(name="price")
	private Double price;
	
	@Column(name="img",columnDefinition = "TEXT")
	private String img;
	
	@Column(name="slug",length = 150)
	private String slug;
	
	@ManyToMany
	@JoinTable(name="product_size", joinColumns = @JoinColumn(name="product_id"),
	inverseJoinColumns = @JoinColumn(name="size_id"))
	private List<EntitySize> sizes = new ArrayList<>();

	@ManyToMany
	@JoinTable(name ="product_color", joinColumns = @JoinColumn(name="product_id"),
	inverseJoinColumns = @JoinColumn(name="color_id"))
	private List<EntityColor> colors = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private EntityCategory category;
	
	public EntityCategory getCategory() {
		return category;
	}

	public void setCategory(EntityCategory category) {
		this.category = category;
	}

	@Column(name="discount")
	private int discount;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}


	public List<EntitySize> getSizes() {
		return sizes;
	}

	public void setSizes(List<EntitySize> sizes) {
		this.sizes = sizes;
	}

	public List<EntityColor> getColors() {
		return colors;
	}

	public void setColors(List<EntityColor> colors) {
		this.colors = colors;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
}
