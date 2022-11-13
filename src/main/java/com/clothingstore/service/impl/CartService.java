package com.clothingstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.clothingstore.Convert.CartConvert;
import com.clothingstore.DTO.CartDTO;
import com.clothingstore.DTO.CartDetailDTO;
import com.clothingstore.DTO.MyUser;
import com.clothingstore.entity.EntityAmount;
import com.clothingstore.entity.EntityCart;
import com.clothingstore.entity.EntityCartDetail;
import com.clothingstore.entity.EntityUser;
import com.clothingstore.exception.BadRequestException;
import com.clothingstore.exception.QuantityCartDetailException;
import com.clothingstore.repository.IAmountRepository;
import com.clothingstore.repository.ICartDetailRepository;
import com.clothingstore.repository.ICartRepository;
import com.clothingstore.repository.IUserRepository;
import com.clothingstore.service.ICartDetailService;
import com.clothingstore.service.ICartService;
import com.clothingstore.util.SecurityUtil;
import com.mysql.cj.protocol.Security;

@Service
public class CartService implements ICartService {

	@Autowired
	private IAmountRepository amountRepository;
	@Autowired
	private IUserRepository iUserRepository;
	@Autowired
	private ICartDetailRepository cartDetailRepository;
	@Autowired
	private ICartDetailService cartDetailService;
	@Autowired
	private CartConvert cartConvert;
	private EntityUser user;
	@Autowired
	private ICartRepository cartRepository;
	@Override
	public CartDTO save(CartDTO cartDTO) {
		EntityUser user = SecurityUtil.getUserByContext(iUserRepository);
		EntityCart cart = new EntityCart();
		return null;
	}

	@Override
	public String SaveOrUpdateCartDetail(CartDetailDTO productShort) {
		try {
		user = SecurityUtil.getUserByContext(iUserRepository);
		EntityCart cart = user.getCart();
		EntityCartDetail cartDetail = null;
		if(cart.getCartDetails() != null && cart.getCartDetails().size() > 0) {
			for (EntityCartDetail detail : cart.getCartDetails()) {
				if (detail.getAmount().getProduct().getId() == productShort.getIdProduct()
						&& detail.getAmount().getColor().getId() == productShort.getIdColor()
						&& detail.getAmount().getSize().getId() == productShort.getIdSize()) {
					cartDetail = detail;
					break;
				}
			}
		}
	 	cartDetailService.saveCartDetail(cartDetail, productShort, cart);
		return "Add cart item successful";
		}catch (Exception e) {
			throw new BadRequestException("Info product incorrect");
		}
	}
	@Override
	public CartDTO findCartOfUser() {
		MyUser myUser  = (MyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return cartConvert.toDTO(cartRepository.findById(myUser.getId()).get());
	}
	
	@Override
	public CartDTO updateCheckAllIsActive() {
		MyUser myUser  = (MyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EntityCart cart = cartRepository.findById(myUser.getId()).get();
		if(cart.getCartDetails()!=null && cart.getCartDetails().size() > 0 ) {
		cart.setIsCheckAll(!cart.getIsCheckAll());
		cartDetailService.updateSelectALLIsActive(cart.getId(), cart.getIsCheckAll());
		cart = cartRepository.save(cart);
		return cartConvert.toDTO(cart);
		}else {
			throw new BadRequestException("There are no products in the cart", cartConvert.toDTO(cart));
		}
	}
	
	@Override
	public CartDTO updateIsActiveIfListCartDetailNull(Long id) {
		cartRepository.updateIsActiveIfListCartDetailNull(id);
		return findCartOfUser();
	}
}
