package com.clothingstore.controller.APIController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clothingstore.DTO.CartDTO;
import com.clothingstore.DTO.CartDetailDTO;
import com.clothingstore.DTO.Short.CartDetailShort;
import com.clothingstore.exception.MessageResponse;
import com.clothingstore.service.ICartDetailService;
import com.clothingstore.service.ICartService;

@RestController("APICart")
@RequestMapping("/api/cart")
public class CartAPI {
	
	@Autowired
	private ICartService cartService;
	
	@Autowired
	private ICartDetailService cartDetailService;
	
	@PostMapping("/add-product")
	public MessageResponse<String> addCart(@RequestBody CartDetailDTO productShort){
		return new MessageResponse<String>(HttpStatus.OK.value(), HttpStatus.OK, "Add cart item success",cartService.SaveOrUpdateCartDetail(productShort));
	}
	
	@GetMapping("/find-cart")
	public MessageResponse<CartDTO> getCart(){
		return new MessageResponse<CartDTO>(HttpStatus.OK.value(),HttpStatus.OK,"Find Cart Of User Successful",cartService.findCartOfUser());
	}
	
	@PutMapping("/update-cart")
	public MessageResponse<CartDTO> updateCart(@RequestBody CartDetailShort cartDetailShort){
		return new MessageResponse<CartDTO>(HttpStatus.OK.value(),HttpStatus.OK,"Update Cart Item Successful",cartDetailService.updateCartDetail(cartDetailShort));
	}
	
	@DeleteMapping("/delete-cart")
	public MessageResponse<CartDTO> deleteCart(@RequestBody List<Long> idCartDetail){
		return new MessageResponse<CartDTO>(HttpStatus.OK.value(),HttpStatus.OK,"Delete Cart Item Successful",cartDetailService.deleteCartDetail(idCartDetail));
	}
	
	@PutMapping("/update-isactive")
	public MessageResponse<CartDTO> updateIsActive(@RequestBody CartDetailShort cartDetail){
		return new MessageResponse<CartDTO>(HttpStatus.OK.value(),HttpStatus.OK,"Check active successful",cartDetailService.updateIsActive(cartDetail.getIdCartDetail()));
	}
	
	@PutMapping("/update-checkall")
	public MessageResponse<CartDTO> updateCheckAllIsActive(){
		return new MessageResponse<CartDTO>(HttpStatus.OK.value(),HttpStatus.OK,"Check active successful",cartService.updateCheckAllIsActive());
	}
	
}
