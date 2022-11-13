package com.clothingstore.controller.APIController;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clothingstore.DTO.OrderDTO;
import com.clothingstore.DTO.OrderDTOInput;
import com.clothingstore.exception.MessageResponse;
import com.clothingstore.repository.IOrderRepository;
import com.clothingstore.service.IOrderService;

@RestController("orderAPI")
@RequestMapping("/api/order")
public class OrderAPI {
	@Autowired
	private IOrderService iOrderService;
	
	@Autowired
	private IOrderRepository iOrderRepository;
	@PostMapping
	public MessageResponse<?> order(@RequestBody OrderDTOInput dto){
		OrderDTO orderDtoView = iOrderService.save(dto);
		return new MessageResponse<OrderDTO>(HttpStatus.OK.value(),HttpStatus.OK,"Order Success",orderDtoView);
	}
	
	@GetMapping
	public MessageResponse<?> getOrder(){
		List<OrderDTO> dtos = iOrderService.findOrderOfUser();
		return new MessageResponse<List<OrderDTO>>(HttpStatus.OK.value(),HttpStatus.OK, "findOrderSuccess",dtos);
	}
	
	@GetMapping("/find-one")
	public MessageResponse<?> getOrder(@RequestParam String code){
		return new MessageResponse<OrderDTO>(HttpStatus.OK.value(),HttpStatus.OK, "findOrderSuccess", iOrderService.findOrderOfUserAndCode(code));
	}
	
	@GetMapping("/admin/get-all-order")
	public MessageResponse<?> getAllOrder(@RequestParam(name = "page",required = false) Integer page,
											@RequestParam(name="size",required = false) Integer size){
		Pageable pageable = null;
		if(page!=null && page > 0 && size !=null && size > 0) {
			page = page -1;
			pageable = PageRequest.of(page, size,Sort.by(Direction.DESC,"orderDate"));
		}
		int count = (int)iOrderRepository.count();
		return new MessageResponse<List<OrderDTO>>(HttpStatus.OK.value(),HttpStatus.OK, "findOrderSuccess", iOrderService.findAllOrder(pageable),count);
	}
	
	@GetMapping("/admin/get-one-order")
	public MessageResponse<OrderDTO> getAllOrder(@RequestParam String code){
		return new MessageResponse<OrderDTO>(HttpStatus.OK.value(),HttpStatus.OK, "findOrderSuccess", iOrderService.findOrderByCode(code));
	}
}
