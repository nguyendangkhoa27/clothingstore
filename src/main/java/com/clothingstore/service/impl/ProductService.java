package com.clothingstore.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.taglibs.standard.lang.jstl.Literal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clothingstore.Convert.CategoryConvert;
import com.clothingstore.Convert.ConvertAmount;
import com.clothingstore.Convert.ProductConvert;
import com.clothingstore.DTO.CategoryDTO;
import com.clothingstore.DTO.ProductDTO;
import com.clothingstore.DTO.Short.AmountShort;
import com.clothingstore.entity.EntityAmount;
import com.clothingstore.entity.EntityCategory;
import com.clothingstore.entity.EntityProduct;
import com.clothingstore.exception.BadRequestException;
import com.clothingstore.exception.IncorrectException;
import com.clothingstore.exception.NotFoundException;
import com.clothingstore.repository.IProductRepository;
import com.clothingstore.service.ICategoryService;
import com.clothingstore.service.IProductService;

import Message.message;

@Service
public class ProductService implements IProductService {

	// INJECT
	@Autowired
	private ProductConvert productConvert;

	@Autowired
	ICategoryService categoryService;

	@Autowired
	private IProductRepository productRepository;

	@Autowired
	private CategoryConvert categoryConvert;

	@Autowired
	private AmountService amountService;
	@Autowired
	private ConvertAmount convertAmount;

	// function
	@Override
	public ProductDTO findOne(Long id) {
		try {
			EntityProduct e = null;
			if (id != null) {
				e = productRepository.findById(id).get();
				if (e != null) {
					return productConvert.toDTO(e);
				}
			}
			throw new NotFoundException(message.messageProductIsNotFound);
		} catch (NotFoundException e) {
			throw e;
		}
	}

	@Override
	@Transactional
	public List<ProductDTO> findAllByCategory(Long categoryId, Pageable pageable) {
		// EntityCategory category = categoryService.findById(categoryId);
		try {
			Integer limit = null;
			Long offset = null;
			if (pageable != null) {
				offset = pageable.getOffset();
				limit = pageable.getPageSize();
			}
			List<EntityProduct> entities = productRepository.findByIdCategory(categoryId, limit, offset); // productRepository.findAll(categoryId);
			if (entities != null && entities.size() > 0) {
				List<ProductDTO> productDTOs = new ArrayList<>();
				for (EntityProduct entityProduct : entities) {
					productDTOs.add(productConvert.toDTO(entityProduct));
				}
				return productDTOs;
			}
			throw new NotFoundException(message.messageProductIsNotFound);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<ProductDTO> findAllByCagorySlug(String categorySlug, Pageable pageable) {
		List<ProductDTO> products = null;
		CategoryDTO cateDTO = categoryService.findByCategorySlug(categorySlug);
		if (cateDTO != null) {
			products = findAllByCategory(cateDTO.getId(), pageable);
			if (products != null && products.size() > 0) {
				return products;
			} else {
				throw new NotFoundException(message.messageProductIsNotFound);
			}
		} else {
			throw new NotFoundException(message.messageCategoryIsNotFound);
		}

	}

	@Override
	@Transactional
	public ProductDTO insert(ProductDTO productDTO) {
		if (productDTO != null) {
			EntityProduct entity = productConvert.toEntity(productDTO);
			if (productDTO.getCategorySlug() != null) {
				entity.setCategory(
						categoryConvert.toEntity(categoryService.findById(productDTO.getCategorySlug().getId())));
			}
			entity.setCreatedDate(new Date());
			entity.setIsActive(true);
			List<EntityAmount> amounts = amountService.saveMultiAmounts(productDTO.getInfoProduct());
			if (amounts != null && amounts.size() > 0) {
				entity.setAmounts(amounts);
				entity = productRepository.save(entity);
				if (entity != null) {
					final EntityProduct temp = entity;
					entity.getAmounts().forEach(t -> t.setProduct(temp));
					entity.setAmounts(amountService.updateMultiAmount(entity.getAmounts()));
					return productConvert.toDTO(entity);
				} else {
					amountService.deleteMultiAmount(amounts);
				}
			} else {
				throw new IncorrectException(message.messageIncorrectSizeOrColor);
			}
		}
		throw new BadRequestException(message.messageBadRequest);

	}

	@Override
	@Transactional
	public List<ProductDTO> insertMultiProduct(List<ProductDTO> products) {
		if (products != null && products.size() > 0) {
			List<EntityProduct> eProducts = new ArrayList<>();
			for (ProductDTO product : products) {
				EntityProduct entity = productConvert.toEntity(product);
				entity.setCategory(categoryConvert
						.toEntity(categoryService.findByCategorySlug(entity.getCategory().getCategorySlug())));
				if (entity.getCategory() == null) {
					return null;
				} else {
					entity.setId(null);
					entity.setCreatedDate(new Date());
					entity.setIsActive(true);
					eProducts.add(entity);
				}
			}

			return productConvert.toListDTO(productRepository.saveAll(eProducts));
		}
		return null;
	}

	@Override
	@Transactional
	public ProductDTO update(ProductDTO productDTO) {
		try {
			if (productDTO != null) {
				EntityProduct newProduct = productConvert.toEntity(productDTO);
				EntityProduct oldProduct = productRepository.findById(productDTO.getId()).get();
				newProduct.setCategory(
						categoryConvert.toEntity(categoryService.findById(productDTO.getCategorySlug().getId())));
				if (newProduct.getCategory() != null) {
					if (oldProduct != null) {
						oldProduct = productConvert.NewToOld(oldProduct, newProduct);
						oldProduct.setModifiedDate(new Date());
						List<EntityAmount> amountsSaveAndUpdate = new ArrayList<>();
						List<EntityAmount> amountsDelete = new ArrayList<>();
						for (EntityAmount amount : oldProduct.getAmounts()) {
							boolean check = true;
							for (AmountShort amountShort : productDTO.getInfoProduct()) {
								if (amount.getColor().getId() == amountShort.getColor().getId()
										&& amount.getSize().getId() == amountShort.getSize().getId()) {
									check = false;
									amount.setAmount(amountShort.getAmount());
									amountsSaveAndUpdate.add(amount);
									productDTO.getInfoProduct().remove(amountShort);
									break;
								}
							}
							if (check == true) {
								amountsDelete.add(amount);
							}
						}
						for (AmountShort amountShor : productDTO.getInfoProduct()) {
							EntityAmount amount = convertAmount.toEntity(amountShor);
							amount.setProduct(oldProduct);
							amountsSaveAndUpdate.add(amount);
						}
						amountService.deleteMultiAmount(amountsDelete);
						amountsSaveAndUpdate = amountService.updateMultiAmount(amountsSaveAndUpdate);
						// List<EntityAmount> amounts =
						oldProduct.setAmounts(amountsSaveAndUpdate);
						oldProduct = productRepository.save(oldProduct);
						if (oldProduct != null) {
							return productConvert.toDTO(oldProduct);
						}
					}
				}
			}
			throw new BadRequestException(message.messageBadRequest);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<ProductDTO> findAll(Pageable pageable) {
		return productConvert.toListDTO(productRepository.findAllByIsActiveOrderByIdAsc(pageable, true));
	}

	@Override
	public Long deleteProduct(List<Long> ids) {
		int i = productRepository.deleteProduct(ids);
		if (i > 0) {
			return (long) i;
		}
		throw new NotFoundException(message.messageProductIsNotFound);
	}

	@Override
	public Integer count(boolean isActive) {
		Integer total = productRepository.countByIsActive(isActive).intValue();
		return total;
	}

	@Override
	public Integer count(boolean isActive,String categorySlug) {
		EntityCategory category = categoryConvert.toEntity(categoryService.findByCategorySlug(categorySlug));
		Integer total = productRepository.countByIsActiveAndCategory(isActive,category).intValue();
		return total;
	}
}
