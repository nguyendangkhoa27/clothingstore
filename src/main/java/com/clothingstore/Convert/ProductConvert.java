package com.clothingstore.Convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clothingstore.DTO.ColorDTO;
import com.clothingstore.DTO.ProductDTO;
import com.clothingstore.DTO.SizeDTO;
import com.clothingstore.DTO.Short.CategoryShortDTO;
import com.clothingstore.DTO.Short.ColorShortDTO;
import com.clothingstore.DTO.Short.SizeShortDTO;
import com.clothingstore.entity.EntityCategory;
import com.clothingstore.entity.EntityColor;
import com.clothingstore.entity.EntityProduct;
import com.clothingstore.entity.EntitySize;

@Component
public class ProductConvert {

	@Autowired
	private SizeConvert sizeConvert;
	
	@Autowired
	private ColorConvert colorConvert;
	
	public EntityProduct toEntity(ProductDTO productDTO) {
		EntityProduct entity = new EntityProduct();
		entity.setId(productDTO.getId());
		if (productDTO.getImg() != null && !productDTO.getImg().isEmpty()) {
			String imgs = "";
			for (String string : productDTO.getImg()) {
				imgs += string + ",";
			}
			entity.setImg(imgs.substring(0, imgs.length() - 1));
		}
		entity.setDiscount(productDTO.getDiscount());
		entity.setIsActive(productDTO.getIsActive());
		entity.setPrice(productDTO.getPrice());
		entity.setSlug(productDTO.getSlug());
		entity.setTitle(productDTO.getTitle());
		entity.setCreatedBy(productDTO.getCreatedBy());
		entity.setCreatedDate(productDTO.getCreatedDate());
		entity.setModifiedBy(productDTO.getModifiedBy());
		entity.setModifiedDate(productDTO.getModifiedDate());
		return entity;
	}

	public ProductDTO toDTO(EntityProduct entityProduct) {
		ProductDTO DTO = null;
		if(entityProduct !=null) {
			DTO =new ProductDTO();
			DTO.setId(entityProduct.getId());
			if (entityProduct.getImg() != null && !entityProduct.getImg().isEmpty()) {
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
			
			DTO.setCategorySlug(new CategoryShortDTO(entityProduct.getCategory().getId(), entityProduct.getCategory().getCategorySlug()));
			DTO.setCreatedBy(entityProduct.getCreatedBy());
			DTO.setCreatedDate(entityProduct.getCreatedDate());
			DTO.setModifiedBy(entityProduct.getModifiedBy());
			DTO.setModifiedDate(entityProduct.getModifiedDate());
			if(entityProduct.getColors() !=null && entityProduct.getColors().size() > 0) {
				List<ColorShortDTO> colors = new ArrayList<>();
				for (EntityColor color : entityProduct.getColors() ) {
					colors.add(new ColorShortDTO(color.getId(), color.getColorName()));
				}
				DTO.setColors(colors);
			}
			if(entityProduct.getSizes() !=null && entityProduct.getSizes().size() > 0) {
				List<SizeShortDTO> sizes = new ArrayList<>();
				for (EntitySize size : entityProduct.getSizes() ) {
					sizes.add(new SizeShortDTO(size.getId(), size.getNameSize()));
				}
				DTO.setSizes(sizes);
			}
		}
		return DTO;
	}

	public EntityProduct NewToOld(EntityProduct oldProduct, EntityProduct newProduct) {

		if (newProduct.getCategory() != null) {
			  oldProduct.setCategory(newProduct.getCategory());
		}
		oldProduct.setImg(newProduct.getImg());
		oldProduct.setIsActive(newProduct.getIsActive());
		oldProduct.setPrice(newProduct.getPrice());
		oldProduct.setSlug(newProduct.getSlug());
		oldProduct.setTitle(newProduct.getTitle());
		oldProduct.setDiscount(newProduct.getDiscount());
		oldProduct.setCreatedBy(newProduct.getCreatedBy());
		oldProduct.setCreatedDate(newProduct.getCreatedDate());
		oldProduct.setModifiedBy(newProduct.getModifiedBy());
		oldProduct.setModifiedDate(newProduct.getModifiedDate());
		return oldProduct;
	}

	public List<ProductDTO> toListDTO(List<EntityProduct> eproducts) {
		List<ProductDTO> productDTO = new ArrayList<>();
		for (EntityProduct eproduct : eproducts) {
			ProductDTO dto = toDTO(eproduct);
			productDTO.add(dto);
		}
		return productDTO;
	}

	public List<EntityProduct> toListEntity(List<ProductDTO> dtoList) {
		List<EntityProduct> entities = new ArrayList<>();
		for (ProductDTO dto : dtoList) {
			EntityProduct entity = toEntity(dto);
			entities.add(entity);
		}
		return entities;
	}
}
