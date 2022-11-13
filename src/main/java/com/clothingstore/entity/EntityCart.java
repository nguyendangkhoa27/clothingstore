package com.clothingstore.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.Id;
@Entity
@Table(name="cart")
public class EntityCart{
	
	@Id
	private Long Id;
	
	@MapsId
	@OneToOne
	private EntityUser user;
	
	@OneToMany(mappedBy = "cart")
	private List<EntityCartDetail> cartDetails = new ArrayList<>();

	@Column(name="is_check_all",columnDefinition = "boolean default false" )
	private Boolean isCheckAll;
	
	
	public Boolean getIsCheckAll() {
		return isCheckAll;
	}

	public void setIsCheckAll(Boolean isCheckAll) {
		this.isCheckAll = isCheckAll;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

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
