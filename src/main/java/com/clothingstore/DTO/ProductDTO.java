package com.clothingstore.DTO;

import java.util.ArrayList;
import java.util.List;

import com.clothingstore.DTO.Short.CategoryShortDTO;
import com.clothingstore.DTO.Short.ColorShortDTO;
import com.clothingstore.DTO.Short.SizeShortDTO;

public class ProductDTO extends AbsDTO {

	private String title;

	private Double price;

	private List<String> img;

	private String slug;

	private List<SizeShortDTO> sizes = new ArrayList<>();

	private List<ColorShortDTO> colors = new ArrayList<>();

	private CategoryShortDTO categorySlug;

	

	public CategoryShortDTO getCategorySlug() {
		return categorySlug;
	}

	public void setCategorySlug(CategoryShortDTO categorySlug) {
		this.categorySlug = categorySlug;
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
	
	public List<SizeShortDTO> getSizes() {
		return sizes;
	}

	public void setSizes(List<SizeShortDTO> sizes) {
		this.sizes = sizes;
	}

	public List<ColorShortDTO> getColors() {
		return colors;
	}

	public void setColors(List<ColorShortDTO> colors) {
		this.colors = colors;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	
}
