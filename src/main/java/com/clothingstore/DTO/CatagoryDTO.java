package com.clothingstore.DTO;

import java.util.List;

public class CatagoryDTO extends AbsDTO {

	
	private String categorySlug;
	
	
	private String img;

	private List<ProductDTO> products;

	public String getCategorySlug() {
		return categorySlug;
	}

	public void setCategorySlug(String categorySlug) {
		this.categorySlug = categorySlug;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}
	
}
