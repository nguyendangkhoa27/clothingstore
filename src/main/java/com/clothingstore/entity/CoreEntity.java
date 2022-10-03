package com.clothingstore.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class CoreEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="created_by")
	private String CreatedBy;
	
	
	@Column(name="modified_date")
	private Date ModifiedDate;
	
	@Column(name="modified_by")
	private String ModifiedBy;
	
	@Column(name="is_active")
	private Boolean isActive;
	
	public Boolean getIsActive() {
		return isActive;
	}


	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getId() {
		return id;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public String getCreatedBy() {
		return CreatedBy;
	}


	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}


	public Date getModifiedDate() {
		return ModifiedDate;
	}


	public void setModifiedDate(Date modifiedDate) {
		ModifiedDate = modifiedDate;
	}


	public String getModifiedBy() {
		return ModifiedBy;
	}


	public void setModifiedBy(String modifiedBy) {
		ModifiedBy = modifiedBy;
	}



}
