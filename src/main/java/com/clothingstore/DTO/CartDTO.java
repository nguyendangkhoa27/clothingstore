package com.clothingstore.DTO;

import java.util.List;
public class CartDTO {
		private Long id;
		
		private List<CartDetailDTO> cartDetail;
		
		private Double totalPrice;
		
		private int totalProduct;
		
		private Boolean checkAll;
		
		public CartDTO() {}
		
		public CartDTO(Long id, List<CartDetailDTO> cartDetail) {
			this.id = id;
			this.cartDetail = cartDetail;
		}
		
		
		public Double getTotalPrice() {
			return totalPrice;
		}

		public void setTotalPrice(Double totalPrice) {
			this.totalPrice = totalPrice;
		}

		public int getTotalProduct() {
			return totalProduct;
		}

		public void setTotalProduct(int totalProduct) {
			this.totalProduct = totalProduct;
		}

		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public List<CartDetailDTO> getcartDetail() {
			return cartDetail;
		}
		public void setcartDetail(List<CartDetailDTO> cartDetail) {
			this.cartDetail = cartDetail;
		}

		public Boolean getCheckAll() {
			return checkAll;
		}

		public void setCheckAll(Boolean checkAll) {
			this.checkAll = checkAll;
		}
		
		
		
		
}
