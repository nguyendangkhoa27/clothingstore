package com.clothingstore.DTO;

import java.util.List;

public class CategoryDTO extends AbsDTO {

	
	private String categorySlug;
	
	
	private List<String> img;

	private int quantity;
	public String getCategorySlug() {
		return categorySlug;
	}

	public void setCategorySlug(String categorySlug) {
		this.categorySlug = categorySlug;
	}

	public List<String> getImg() {
		return img;
	}

	public void setImg(List<String> img) {
		this.img = img;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
}
