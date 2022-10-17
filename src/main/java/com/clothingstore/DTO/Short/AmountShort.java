package com.clothingstore.DTO.Short;

public class AmountShort {
	private ColorShortDTO color;
	
	private SizeShortDTO size;
	
	private int amount;

	public ColorShortDTO getColor() {
		return color;
	}

	public void setColor(ColorShortDTO colorShortDTO) {
		this.color = colorShortDTO;
	}

	public SizeShortDTO getSize() {
		return size;
	}

	public void setSize(SizeShortDTO sizeShortDTO) {
		this.size = sizeShortDTO;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
