package com.clothingstore.service;

import com.clothingstore.DTO.CartDTO;
import com.clothingstore.DTO.CartDetailDTO;

public interface ICartService {
		CartDTO save(CartDTO cartDTO);
		String SaveOrUpdateCartDetail(CartDetailDTO productShort);
		CartDTO findCartOfUser();
		CartDTO updateCheckAllIsActive();
		CartDTO updateIsActiveIfListCartDetailNull(Long id);
}
