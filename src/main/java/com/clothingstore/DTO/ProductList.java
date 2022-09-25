package com.clothingstore.DTO;

import java.util.ArrayList;
import java.util.List;

public class ProductList {
	
	public List<ProductDTO> list = new ArrayList<>();

	public List<ProductDTO> getList() {
		return list;
	}

	public void setList(List<ProductDTO> list) {
		this.list = list;
	}
	
	
}
