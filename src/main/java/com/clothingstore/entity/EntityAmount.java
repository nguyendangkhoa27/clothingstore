package com.clothingstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="amount")
public class EntityAmount extends CoreEntity{
	
	@ManyToOne
	@JoinColumn(name="size_id")
	private EntitySize size;
	@ManyToOne
	@JoinColumn(name="color_id")
	private EntityColor color;
	
	@ManyToOne
	@JoinColumn(name="product_id",nullable = true)
	private EntityProduct product;
	@Column(name="amount")
	private int amount;
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public EntitySize getSize() {
		return size;
	}

	public void setSize(EntitySize size) {
		this.size = size;
	}

	public EntityColor getColor() {
		return color;
	}

	public void setColor(EntityColor color) {
		this.color = color;
	}

	public EntityProduct getProduct() {
		return product;
	}

	public void setProduct(EntityProduct product) {
		this.product = product;
	}
	
	
}
