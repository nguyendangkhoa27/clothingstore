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
	private Integer quantity;
	
	@Column
	private Double totalPrice;
	
	public EntityCart getCart() {
		return cart;
	}

	public void setCart(EntityCart cart) {
		this.cart = cart;
	}
	
	
}
