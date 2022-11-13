package com.clothingstore.DTO;

import java.text.DecimalFormat;
import java.util.List;

public class CartDetailDTO {
	private Long idCartdetail;
	private Long idProduct;
	private List<String> imgs;
	private String titleProduct;
	private Double price;
	private Long idSize;
	private String nameSize;
	private Long idColor;
	private String nameColor;
	private int quantity;
	private int amountProduct;
	private Boolean isActive;
	public CartDetailDTO() {}
	
	public CartDetailDTO(Long id, Double price) {
		super();
		this.idProduct = id;
		this.price = price;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public int getAmountProduct() {
		return amountProduct;
	}

	public void setAmountProduct(int amountProduct) {
		this.amountProduct = amountProduct;
	}

	public List<String> getImgs() {
		return imgs;
	}

	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Long getIdSize() {
		return idSize;
	}

	public void setIdSize(Long idSize) {
		this.idSize = idSize;
	}

	public Long getIdColor() {
		return idColor;
	}

	public void setIdColor(Long idColor) {
		this.idColor = idColor;
	}
	
	public Double getPrice() {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(340);
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getIdCartdetail() {
		return idCartdetail;
	}

	public void setIdCartdetail(Long idCartdetail) {
		this.idCartdetail = idCartdetail;
	}

	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public String getTitleProduct() {
		return titleProduct;
	}

	public void setTitleProduct(String titleProduct) {
		this.titleProduct = titleProduct;
	}

	public String getNameSize() {
		return nameSize;
	}

	public void setNameSize(String nameSize) {
		this.nameSize = nameSize;
	}

	public String getNameColor() {
		return nameColor;
	}

	public void setNameColor(String nameColor) {
		this.nameColor = nameColor;
	}
	
	
}
