package com.pav2py.excel.model.nvm;

import com.pav2py.excel.model.abstraction.AbstractVariantRow;

public class NVMVariantRow extends AbstractVariantRow{

	private String boschNumber;
	private String pts;

	public String getBoschNumber() {
		return boschNumber;
	}

	public void setBoschNumber(String boschNumber) {
		this.boschNumber = boschNumber;
	}

	public String getPts() {
		return pts;
	}

	public void setPts(String pts) {
		this.pts = pts;
	}

	@Override
	public String toPy() {
		// TODO Auto-generated method stub
		return null;
	}

}
