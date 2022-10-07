package com.clothingstore.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clothingstore.Convert.CategoryConvert;
import com.clothingstore.Convert.ProductConvert;
import com.clothingstore.DTO.CategoryDTO;
import com.clothingstore.DTO.ProductDTO;
import com.clothingstore.entity.EntityProduct;
import com.clothingstore.exception.BadRequestException;
import com.clothingstore.exception.NotFoundException;
import com.clothingstore.repository.IProductRepository;
import com.clothingstore.service.ICategoryService;
import com.clothingstore.service.IColorService;
import com.clothingstore.service.IProductService;
import com.clothingstore.service.ISizeService;

import Message.message;
@Service
public class ProductService implements IProductService{
	
	//INJECT
	@Autowired
	private ProductConvert productConvert;
	
	
	@Autowired ICategoryService categoryService; 
	
	@Autowired
	private IProductRepository productRepository;
	
	@Autowired
	private CategoryConvert categoryConvert;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private ISizeService sizeService;
	
	//function
	@Override
	public ProductDTO findOne(Long id) {
		try {
			EntityProduct e = null;
			if(id != null) {
				e = productRepository.findById(id).get();
				if(e!=null) {
					return productConvert.toDTO(e);
				}
			}
			throw new NotFoundException("Không có sản phẩm này với mã id =" +id);
		}catch(Exception e) {
			throw new NotFoundException("Không có sản phẩm này với mã id =" +id);
		}
	}
	
	@Override
	@Transactional
	public List<ProductDTO> findAllByCategory(Long categoryId){
		//EntityCategory category = categoryService.findById(categoryId);
		List<EntityProduct> entities = productRepository.findByIdCategory(categoryId); //productRepository.findAll(categoryId);
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
		try {
			if(productDTO !=null) {
				EntityProduct entity = productConvert.toEntity(productDTO);
				if(productDTO.getCategorySlug() != null) {
					entity.setCategory(categoryConvert.toEntity(categoryService.findById(productDTO.getCategorySlug().getId())));
				}
					entity.setCreatedDate(new Date());
					entity.setColors(colorService.findByColorIds(productDTO.getColors()));
					entity.setSizes(sizeService.findSizeByIds(productDTO.getSizes()));
					entity.setIsActive(true);
					entity = productRepository.save(entity);
					return productConvert.toDTO(entity);	
			}
			throw new NotFoundException("Sản phẩm này là rỗng");
		}catch (Exception e) {
			throw new BadRequestException(message.messageBadRequest);		}
		
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
						entity.setId(null);
						entity.setCreatedDate(new Date());
						entity.setColors(colorService.findByColorIds(product.getColors()));
						entity.setSizes(sizeService.findSizeByIds(product.getSizes()));
						entity.setIsActive(true);
						eProducts.add(entity);
					}
				}
				
				return  productConvert.toListDTO(productRepository.saveAll(eProducts));
			}
		return null;
	}
	
	@Override
	@Transactional
	public ProductDTO update(ProductDTO productDTO) {
		try {
				if(productDTO!=null) {
					EntityProduct newProduct = productConvert.toEntity(productDTO);
					EntityProduct oldProduct = productRepository.findById(productDTO.getId()).get();
					newProduct.setCategory(categoryConvert.toEntity(categoryService.findByCategorySlug(newProduct.getCategory().getCategorySlug())));
					if(newProduct.getCategory() !=null) {
						if(oldProduct!=null) {
								oldProduct = productConvert.NewToOld(oldProduct, newProduct);
								oldProduct.setModifiedDate(new Date());
								oldProduct.setColors(colorService.findByColorIds(productDTO.getColors()));
								oldProduct.setSizes(sizeService.findSizeByIds(productDTO.getSizes()));
								oldProduct = productRepository.save(oldProduct);
								if(oldProduct != null) {
									return productConvert.toDTO(oldProduct);
								}
						}
					}
				}
				throw new BadRequestException(message.messageBadRequest);
		}catch (Exception e) {
			throw new BadRequestException(message.messageBadRequest);
		}
	}
	@Override
	public List<ProductDTO> findAll() {
		return productConvert.toListDTO(productRepository.findAll());
	}
	
	@Override
	public Long deleteProduct(List<Long> ids) {
		int i = productRepository.deleteProduct(ids);
		if(i > 0) {
			return (long) i;
		}
		throw new NotFoundException("Không có sản phẩm này");
	}
	
}
