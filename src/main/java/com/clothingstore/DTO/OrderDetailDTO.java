package com.clothingstore.DTO;

import java.util.List;

public class OrderDetailDTO {
	private Long idOrderDetail;
	private Long idProduct;
	private List<String> imgs;
	private String titleProduct;
	private Double price;
	private Long idSize;
	private String nameSize;
	private Long idColor;
	private String nameColor;
	private int quantity;
	public Long getIdOrderDetail() {
		return idOrderDetail;
	}
	public void setIdOrderDetail(Long idOrderDetail) {
		this.idOrderDetail = idOrderDetail;
	}
	public Long getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}
	public List<String> getImgs() {
		return imgs;
	}
	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
	}
	public String getTitleProduct() {
		return titleProduct;
	}
	public void setTitleProduct(String titleProduct) {
		this.titleProduct = titleProduct;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Long getIdSize() {
		return idSize;
	}
	public void setIdSize(Long idSize) {
		this.idSize = idSize;
	}
	public String getNameSize() {
		return nameSize;
	}
	public void setNameSize(String nameSize) {
		this.nameSize = nameSize;
	}
	public Long getIdColor() {
		return idColor;
	}
	public void setIdColor(Long idColor) {
		this.idColor = idColor;
	}
	public String getNameColor() {
		return nameColor;
	}
	public void setNameColor(String nameColor) {
		this.nameColor = nameColor;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

	
}
