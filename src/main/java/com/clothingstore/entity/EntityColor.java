package com.clothingstore.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="color")
public class EntityColor extends CoreEntity {
		@Column(name="color_name",length = 150,nullable = false, unique = true)
		private String colorName;
		
		@OneToMany(mappedBy = "color")
		private List<EntityAmount> amount;
		
		public String getColorName() {
			return colorName;
		}

		public void setColorName(String colorName) {
			this.colorName = colorName;
		}

		
}
