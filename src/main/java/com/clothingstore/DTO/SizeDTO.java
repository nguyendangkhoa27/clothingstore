package com.clothingstore.DTO;

import java.util.Set;

public class SizeDTO extends AbsDTO {

	private String nameSize;

	private Set<ProductDTO> products;

	public String getNameSize() {
		return nameSize;
	}

	public void setNameSize(String nameSize) {
		this.nameSize = nameSize;
	}

	public Set<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductDTO> products) {
		this.products = products;
	}

}
