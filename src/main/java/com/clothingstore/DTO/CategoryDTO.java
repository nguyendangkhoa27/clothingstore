package com.clothingstore.DTO;

import java.util.List;

public class CategoryDTO extends AbsDTO {

	
	private String categorySlug;
	
	
	private List<String> img;

	private List<ProductDTO> products;

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

	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}
	
}
