package com.clothingstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_detail")
public class EntityOrderDetail extends CoreEntity {

	@ManyToOne
	@JoinColumn(name="orders_code")
	private EntityOrder orders;
	
	@ManyToOne
	@JoinColumn(name="amount_id")
	private EntityAmount amount;
	
	@Column(name="quantity")
	private Integer quantity;
	
	@Column(name="total_price")
	private Double totalPrice;

	public EntityOrder getOrders() {
		return orders;
	}

	public void setOrders(EntityOrder orders) {
		this.orders = orders;
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
