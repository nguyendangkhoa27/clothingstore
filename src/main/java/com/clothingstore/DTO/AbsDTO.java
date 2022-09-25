package com.clothingstore.DTO;

import java.util.Date;

public abstract class AbsDTO {
	private Long id;
	private Date createdDate;

	private String CreatedBy;

	private Date ModifiedDate;

	private String ModifiedBy;

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

	public Date getCreateDate() {
		return createdDate;
	}

	public String getCreateBy() {
		return CreatedBy;
	}

	public Date getModifiedDate() {
		return ModifiedDate;
	}

	public String getModifiedBy() {
		return ModifiedBy;
	}

}
