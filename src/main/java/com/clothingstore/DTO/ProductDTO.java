package com.clothingstore.DTO;

import java.util.ArrayList;
import java.util.List;

public class ProductDTO extends AbsDTO {

	private String title;

	private Double price;

	private List<String> img;

	private String slug;

	private List<SizeDTO> sizes = new ArrayList<>();

	private List<ColorDTO> colors = new ArrayList<>();

	private CatagoryDTO category;

	public CatagoryDTO getCategory() {
		return category;
	}

	public void setCategory(CatagoryDTO category) {
		this.category = category;
	}

	private int discount;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	

	public List<String> getImg() {
		return img;
	}

	public void setImg(List<String> img) {
		this.img = img;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public List<SizeDTO> getSizes() {
		return sizes;
	}

	public void setSizes(List<SizeDTO> sizes) {
		this.sizes = sizes;
	}

	public List<ColorDTO> getColors() {
		return colors;
	}

	public void setColors(List<ColorDTO> colors) {
		this.colors = colors;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

}
