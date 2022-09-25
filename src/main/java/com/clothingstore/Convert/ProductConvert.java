package com.clothingstore.Convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clothingstore.DTO.ProductDTO;
import com.clothingstore.entity.EntityProduct;
import com.clothingstore.service.ICategoryService;

@Component
public class ProductConvert {

	@Autowired
	private ICategoryService categoryServiced;
	public EntityProduct toEntity(ProductDTO productDTO) {
		EntityProduct entity = new EntityProduct();
		entity.setId(productDTO.getId());
		if(productDTO.getCategory()!=null) {
		entity.setCategory(categoryServiced.findById(productDTO.getCategory().getId()));
		}
		if(productDTO.getImg() !=null && !productDTO.getImg().isEmpty()) {
			String imgs = "";
			for(String string : productDTO.getImg()) {
				imgs += string+",";
			}
			entity.setImg(imgs.substring(0, imgs.length()-1));
		}
		entity.setDiscount(productDTO.getDiscount());
		entity.setIsActive(productDTO.getIsActive());
		entity.setPrice(productDTO.getPrice());
		entity.setSlug(productDTO.getSlug());
		entity.setTitle(productDTO.getTitle());
		return entity;
	}
	
	public ProductDTO toDTO(EntityProduct entityProduct) {
		ProductDTO DTO = new ProductDTO();
		DTO.setId(entityProduct.getId());
		if(entityProduct.getImg() !=null && !entityProduct.getImg().isEmpty()) {
			String[] img = entityProduct.getImg().split(",");
			List<String> imgs = new ArrayList<>();
			for (String string : img) {
				imgs.add(string);
			}
			DTO.setImg(imgs);
		}
		DTO.setIsActive(entityProduct.getIsActive());
		DTO.setPrice(entityProduct.getPrice());
		DTO.setSlug(entityProduct.getSlug());
		DTO.setTitle(entityProduct.getTitle());
		DTO.setDiscount(entityProduct.getDiscount());
		return DTO;
	}
	
	public EntityProduct NewToOld (EntityProduct oldProduct, EntityProduct newProduct) {
		
		if(newProduct.getCategory()!=null) {
			oldProduct.setCategory(categoryServiced.findById(newProduct.getCategory().getId()));
			}
		oldProduct.setImg(newProduct.getImg());
		oldProduct.setIsActive(newProduct.getIsActive());
		oldProduct.setPrice(newProduct.getPrice());
		oldProduct.setSlug(newProduct.getSlug());
		oldProduct.setTitle(newProduct.getTitle());
		oldProduct.setDiscount(newProduct.getDiscount());
		return oldProduct;
	}
	
	public List<ProductDTO> toListDTO(List<EntityProduct> eproducts){
		List<ProductDTO> productDTO = new ArrayList<>();
		for(EntityProduct eproduct : eproducts) {
			ProductDTO dto = toDTO(eproduct);
			productDTO.add(dto);
		}
		return productDTO;
	}
}
