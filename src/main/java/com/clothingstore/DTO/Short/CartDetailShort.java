package com.clothingstore.DTO.Short;

public class CartDetailShort {
	private Long idCartDetail;
	
	private Integer quantity;

	public CartDetailShort() {}
	public CartDetailShort(Long idCartDetail,Integer quantity) {
		
	}
	public Long getIdCartDetail() {
		return idCartDetail;
	}

	public void setIdCartDetail(Long idCartDetail) {
		this.idCartDetail = idCartDetail;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
