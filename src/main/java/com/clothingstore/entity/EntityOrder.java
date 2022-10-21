package com.clothingstore.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class EntityOrder {
	@Id
	private String Code;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private EntityUser user;
	
	@Column(name="order_date")
	private Date orderDate;
	
	@Column(name = "status_shipping")
	private String statusShipping;
	
	@Column(name = "status_payment")
	private String statusPayment;
	
	@OneToMany(mappedBy = "orders")
	private List<EntityOrderDetail> details;

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public EntityUser getUser() {
		return user;
	}

	public void setUser(EntityUser user) {
		this.user = user;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getStatusShipping() {
		return statusShipping;
	}

	public void setStatusShipping(String statusShipping) {
		this.statusShipping = statusShipping;
	}

	public String getStatusPayment() {
		return statusPayment;
	}

	public void setStatusPayment(String statusPayment) {
		this.statusPayment = statusPayment;
	}

	public List<EntityOrderDetail> getDetails() {
		return details;
	}

	public void setDetails(List<EntityOrderDetail> details) {
		this.details = details;
	}
	
	
}
