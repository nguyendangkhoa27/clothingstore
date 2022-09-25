package com.clothingstore.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="color")
public class EntityColor extends CoreEntity {
		@Column(name="color_name",length = 150)
		private String colorName;
		
		@ManyToMany(mappedBy ="colors")
		private List<EntityProduct> products = new ArrayList<>();

		public String getColorName() {
			return colorName;
		}

		public void setColorName(String colorName) {
			this.colorName = colorName;
		}

		public List<EntityProduct> getProducts() {
			return products;
		}

		public void setProducts(List<EntityProduct> products) {
			this.products = products;
		}
		
}
