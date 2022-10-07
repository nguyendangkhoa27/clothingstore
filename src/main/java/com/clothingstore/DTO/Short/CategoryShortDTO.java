package com.clothingstore.DTO.Short;

public class CategoryShortDTO {
	private Long id;
	private String categoryName;
	
	public CategoryShortDTO() {}
	public CategoryShortDTO(Long id, String categoryName) {
		this.id = id;
		this.categoryName = categoryName;
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}
