package com.clothingstore.service;

import com.clothingstore.entity.EntityCategory;

public interface ICategoryService {
	EntityCategory findById(Long id);
}
