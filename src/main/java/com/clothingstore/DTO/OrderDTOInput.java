package com.clothingstore.DTO;

import java.util.Date;
import java.util.List;

public class OrderDTOInput {
	private String Code;

	private String email;

	private String firstName;

	private String lastName;

	private String phoneNumber;	

	private String Address;

	private Date orderDate;

	private boolean isOnline;

	private Double totalPriceOrder;

	private List<Long> idCartDetail;

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
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

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public boolean getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public Double getTotalPriceOrder() {
		return totalPriceOrder;
	}

	public void setTotalPriceOrder(Double totalPriceOrder) {
		this.totalPriceOrder = totalPriceOrder;
	}

	public List<Long> getIdCartDetail() {
		return idCartDetail;
	}

	public void setIdCartDetail(List<Long> idCartDetail) {
		this.idCartDetail = idCartDetail;
	}

	

	
	
}
