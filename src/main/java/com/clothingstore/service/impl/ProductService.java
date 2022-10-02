package com.clothingstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clothingstore.Convert.CategoryConvert;
import com.clothingstore.Convert.ProductConvert;
import com.clothingstore.DTO.CategoryDTO;
import com.clothingstore.DTO.ProductDTO;
import com.clothingstore.entity.EntityProduct;
import com.clothingstore.repository.impl.CustomProductRepository;
import com.clothingstore.service.ICategoryService;
import com.clothingstore.service.IProductService;
@Service
public class ProductService implements IProductService{
	
	//INJECT
	@Autowired
	private ProductConvert productConvert;
	@Autowired
	private CustomProductRepository productRepository;
	
	@Autowired ICategoryService categoryService; 
	
	@Autowired
	private CategoryConvert categoryConvert;
	
	//function
	@Override
	public ProductDTO findOne(Long id) {
		if(id != null) {
			EntityProduct e = productRepository.findOne(id);
			return productConvert.toDTO(e);
		}
		return null;
	}
	
	@Override
	@Transactional
	public List<ProductDTO> findAllByCategory(Long categoryId){
		//EntityCategory category = categoryService.findById(categoryId);
		List<EntityProduct> entities = productRepository.findAll(categoryId);
		List<ProductDTO> productDTOs = new ArrayList<>();
		for (EntityProduct entityProduct : entities) {
			productDTOs.add(productConvert.toDTO(entityProduct));
		}
			return productDTOs;
	}
	
	@Override
	public List<ProductDTO> findAllByCagorySlug(String categorySlug) {
		List<ProductDTO> products = null;
		CategoryDTO cateDTO = categoryService.findByCategorySlug(categorySlug);
		if(cateDTO != null) {
			products = findAllByCategory(cateDTO.getId());
		}
		return products;
	}
	
	@Override
	@Transactional
	public ProductDTO insert(ProductDTO productDTO) {
		if(productDTO !=null) {
			EntityProduct entity = productConvert.toEntity(productDTO);
			entity.setCategory(categoryConvert.toEntity(categoryService.findByCategorySlug(entity.getCategory().getCategorySlug())));
			if(entity.getCategory() !=null) {
				productRepository.save(entity);
				return productConvert.toDTO(entity);
			}
		}
		return null;
	}
	
	@Override
	@Transactional
	public List<ProductDTO> insertMultiProduct(List<ProductDTO> products) {
			if(products != null && products.size() > 0) {
				List<EntityProduct> eProducts = new ArrayList<>();
				for(ProductDTO product : products) {
					EntityProduct entity = productConvert.toEntity(product);
					entity.setCategory(categoryConvert.toEntity(categoryService.findByCategorySlug(entity.getCategory().getCategorySlug())));
					if(entity.getCategory() == null) {
						return null;	
					}else {
						eProducts.add(entity);
					}
				}
				
				return  productConvert.toListDTO(productRepository.saveMultiProduct(eProducts));
			}
		return null;
	}
	
	@Override
	@Transactional
	public ProductDTO update(ProductDTO productDTO) {
		if(productDTO!=null) {
			EntityProduct newProduct = productConvert.toEntity(productDTO);
			EntityProduct oldProduct = productRepository.findOne(productDTO.getId());
			if(newProduct.getCategory() !=null) {
				if(oldProduct!=null) {
						oldProduct = productConvert.NewToOld(oldProduct, newProduct);
						productRepository.save(oldProduct);
						return productConvert.toDTO(oldProduct);
				}
			}
		}
		return null;
	}
	@Override
	public List<ProductDTO> findAll() {
		return productConvert.toListDTO(productRepository.findAll());
	}
	
}
