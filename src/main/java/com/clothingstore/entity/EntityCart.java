package com.clothingstore.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Id;
@Entity
@Table(name="cart")
public class EntityCart{
	
	@Id
	private Long Id;
	
	@MapsId
	@OneToOne
	@JoinColumn(name="user_id")
	private EntityUser user;
	
	@OneToMany(mappedBy = "cart")
	private List<EntityCartDetail> cartDetails;

	
	public List<EntityCartDetail> getCartDetails() {
		return cartDetails;
	}

	public void setCartDetails(List<EntityCartDetail> cartDetails) {
		this.cartDetails = cartDetails;
	}

	
	public EntityUser getUser() {
		return user;
	}

	public void setUser(EntityUser user) {
		this.user = user;
	}
	
	
}
