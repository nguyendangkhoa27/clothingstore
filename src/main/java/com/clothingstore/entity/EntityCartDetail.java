package com.clothingstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cart_detail")
public class EntityCartDetail extends CoreEntity {

	@ManyToOne
	@JoinColumn(name="cart_id")
	private EntityCart cart;

	@ManyToOne
	@JoinColumn(name="amount_id")
	private EntityAmount amount;
	
	@Column
	private Integer quantity = 0;
	
	@Column
	private Double totalPrice;
	
	public EntityCart getCart() {
		return cart;
	}

	public void setCart(EntityCart cart) {
		this.cart = cart;
	}

	public EntityAmount getAmount() {
		return amount;
	}

	public void setAmount(EntityAmount amount) {
		this.amount = amount;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	 
	
	
	
}
