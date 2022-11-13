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
	private String code;
	
	@Column(name="email",nullable = false)
	private String email;
	
	@Column(name="first_name",nullable = false)
	private String firstName;
	
	@Column(name="last_name",nullable = false)
	private String lastName;
	
	@Column(name="phone_number",nullable = false)
	private String phoneNumber;
	
	@Column(name="address",nullable = false)
	private String Address;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private EntityUser user;
	
	@Column(name="order_date")
	private Date orderDate;
	
	@Column(name = "status_shipping")
	private String statusShipping;
	
	@Column(name = "is_online")
	private boolean isOnline;
	
	@Column(name="total_price_orders")
	private Double totalPriceOrder;
	@OneToMany(mappedBy = "orders")
	private List<EntityOrderDetail> details;	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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


	public boolean getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public List<EntityOrderDetail> getDetails() {
		return details;
	}

	public void setDetails(List<EntityOrderDetail> details) {
		this.details = details;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public Double getTotalPriceOrder() {
		return totalPriceOrder;
	}

	public void setTotalPriceOrder(Double totalPriceOrder) {
		this.totalPriceOrder = totalPriceOrder;
	}
	
	
}
