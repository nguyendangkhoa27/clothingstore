package com.clothingstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.clothingstore.Convert.CartConvert;
import com.clothingstore.Convert.CartDetailConvert;
import com.clothingstore.DTO.CartDTO;
import com.clothingstore.DTO.CartDetailDTO;
import com.clothingstore.DTO.MyUser;
import com.clothingstore.DTO.Short.CartDetailShort;
import com.clothingstore.entity.EntityAmount;
import com.clothingstore.entity.EntityCart;
import com.clothingstore.entity.EntityCartDetail;
import com.clothingstore.entity.EntityProduct;
import com.clothingstore.entity.EntityUser;
import com.clothingstore.exception.BadRequestException;
import com.clothingstore.exception.NotFoundException;
import com.clothingstore.exception.QuantityCartDetailException;
import com.clothingstore.repository.IAmountRepository;
import com.clothingstore.repository.ICartDetailRepository;
import com.clothingstore.repository.ICartRepository;
import com.clothingstore.repository.IUserRepository;
import com.clothingstore.service.ICartDetailService;
import com.clothingstore.service.ICartService;
import com.clothingstore.util.SecurityUtil;

@Service
public class CartDetailService implements ICartDetailService {
	@Autowired
	private ICartDetailRepository cartDetailRepository;
	@Autowired
	private IAmountRepository amountRepository;

	@Autowired
	private CartDetailConvert cartDetailConvert;

	@Autowired
	private IUserRepository iUserRepository;

	@Autowired
	private CartConvert cartConvert;
	
	@Autowired
	private ICartService cartService; 
	
	@Autowired
	private ICartRepository cartRepository;

	@Override
	public CartDetailDTO saveCartDetail(EntityCartDetail cartDetailEntity, CartDetailDTO cartDetailDTO,
			EntityCart cart) {
		if (cartDetailDTO.getQuantity() > 0) {
			EntityAmount amount;
			if (cartDetailEntity == null) {
				cartDetailEntity = new EntityCartDetail();
				if(cartDetailDTO.getIdSize() != null && cartDetailDTO.getIdSize() > 0) {
				amount = amountRepository.findByIdProductAndIdSizeAndIdColor(cartDetailDTO.getIdProduct(),
						cartDetailDTO.getIdSize(), cartDetailDTO.getIdColor()).get(0);
				}else {
					amount = amountRepository.findByIdProductAndIdColor(cartDetailDTO.getIdProduct(),
							cartDetailDTO.getIdColor()).get(0);
				}
				cartDetailEntity.setAmount(amount);
				cartDetailEntity.setCart(cart);
				cartDetailEntity.setIsActive(false);
			} else {
				amount = cartDetailEntity.getAmount();
			}
			cartDetailEntity.setQuantity(cartDetailDTO.getQuantity() + cartDetailEntity.getQuantity());
			if (cartDetailEntity.getQuantity() <= amount.getAmount()) {
				EntityProduct product = cartDetailEntity.getAmount().getProduct();
//				Double totalPrice = product.getPrice() * cartDetailEntity.getQuantity() * (100 - product.getDiscount())
//						/ 100;
				cartDetailEntity.setTotalPrice(product.getPrice());
				cartDetailEntity = cartDetailRepository.save(cartDetailEntity);
				cart.setIsCheckAll(false);
				cart = cartRepository.save(cart);
				return cartDetailConvert.toDTO(cartDetailEntity);
			} else {
				throw new QuantityCartDetailException("Incorrect quantity");
			}
		} else {
			throw new QuantityCartDetailException("Incorrect quantity!!! ");
		}
	}

	@Override
	public CartDTO updateCartDetail(CartDetailShort cartDetailShort) {
		try {
			MyUser myUser = (MyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			List<EntityCartDetail> cartDetails = cartDetailRepository.findByIdCart(myUser.getId());
			for (EntityCartDetail e : cartDetails) {
				if (e.getId() == cartDetailShort.getIdCartDetail()) {
					EntityAmount amount = cartDetails.get(0).getAmount();
					EntityProduct product = amount.getProduct();
					if(cartDetailShort.getQuantity() <= amount.getAmount()) {
						e.setQuantity(cartDetailShort.getQuantity());
//						Double totalPrice = product.getPrice() * e.getQuantity() * (100 - product.getDiscount())
//								/ 100;
						e.setTotalPrice(product.getPrice());
						return cartConvert.toDTO(cartDetailRepository.save(e).getCart());
					}else {
						return cartService.findCartOfUser();
					}
				}
			}
			throw new NotFoundException("No products found in the shopping cart!!!");
		} catch (Exception e) {
			throw e;
		}
	}
	@Override
	public CartDTO deleteCartDetail(List<Long> idCartDetails) {
		MyUser myUser = (MyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer check = cartDetailRepository.deleteById(myUser.getId(), idCartDetails);
		if(check != null && check > 0 ) {
			CartDTO cartDTO = cartService.findCartOfUser();
			if(cartDTO.getcartDetail()==null || cartDTO.getcartDetail().size() <=0) {
				cartDTO = cartService.updateIsActiveIfListCartDetailNull(cartDTO.getId());
			}
			return cartDTO;
		}
			throw new BadRequestException("This product is not in the cart!!!");
		
	}
	@Override
	public CartDTO updateIsActive(Long id) {
		MyUser myUser = (MyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EntityCartDetail cartDetail = cartDetailRepository.findByIdCartDetailAndUser(myUser.getId(), id);
		if(cartDetail==null) {
			throw new NotFoundException("Not found product in cart!");
		}
		cartDetail.setIsActive(!cartDetail.getIsActive());
		EntityCart cart = cartDetailRepository.save(cartDetail).getCart();
		List<EntityCartDetail> cartDetails = cart.getCartDetails();
		int check = 0;
		for (EntityCartDetail entityCartDetail : cartDetails) {
			if(entityCartDetail.getIsActive() == true) {
				check++;
			}
		}
		if(check == cartDetails.size()) {
			cart.setIsCheckAll(true);
			cart = cartRepository.save(cart);
		}else if(check < cartDetails.size() && check >= 0){
			cart.setIsCheckAll(false);
			cart = cartRepository.save(cart);
		}
		return cartConvert.toDTO(cart);
	}
	
	@Override
	public int updateSelectALLIsActive(Long userId, boolean isActive) {
		int check = cartDetailRepository.updateIsActiveByUserId(userId, isActive);
		return check;
	}
}
