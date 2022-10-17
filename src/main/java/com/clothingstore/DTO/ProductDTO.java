package com.clothingstore.DTO;

import java.util.ArrayList;
import java.util.List;

import com.clothingstore.DTO.Short.AmountShort;
import com.clothingstore.DTO.Short.CategoryShortDTO;

public class ProductDTO extends AbsDTO {

	private String title;

	private Double price;

	private List<String> img;

	private String slug;

	private List<AmountShort> infoProduct = new ArrayList<>();

	private CategoryShortDTO categorySlug;

	public List<AmountShort> getInfoProduct() {
		return infoProduct;
	}

	public void setInfoProduct(List<AmountShort> infoProduct) {
		this.infoProduct = infoProduct;
	}

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
	

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	
}
