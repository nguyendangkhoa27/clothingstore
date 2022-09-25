package com.clothingstore.DTO;

import java.util.ArrayList;
import java.util.List;


public class ColorDTO extends AbsDTO {
		
		private String colorName;
		
		
		private List<ProductDTO> products = new ArrayList<>();

		public String getColorName() {
			return colorName;
		}

		public void setColorName(String colorName) {
			this.colorName = colorName;
		}

		public List<ProductDTO> getProducts() {
			return products;
		}

		public void setProducts(List<ProductDTO> products) {
			this.products = products;
		}
		
}
