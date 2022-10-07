package com.clothingstore.DTO.Short;

public class SizeShortDTO {
	private Long id;
	private String sizeName;
	public SizeShortDTO() {}
	
	public SizeShortDTO(Long id, String sizeName) {
		this.id = id;
		this.sizeName = sizeName;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSizeName() {
		return sizeName;
	}
	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}
	
	
}
