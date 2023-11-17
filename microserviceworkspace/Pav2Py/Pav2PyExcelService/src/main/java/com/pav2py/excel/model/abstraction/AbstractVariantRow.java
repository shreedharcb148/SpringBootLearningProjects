package com.pav2py.excel.model.abstraction;

import java.util.List;

import com.pav2py.excel.model.nvm.ConstantRow;

public abstract class AbstractVariantRow implements IRow{

	private int ecuVariant;
	private String ecuVariantDescription;
	private List<ConstantRow> constants;    
	private boolean valid;
	private String validationMessage;


	public int getEcuVariant() {
		return ecuVariant;
	}

	public void setEcuVariant(int ecuVariant) {
		this.ecuVariant = ecuVariant;
	}

	public String getEcuVariantDescription() {
		return ecuVariantDescription;
	}

	public void setEcuVariantDescription(String ecuVariantDescription) {
		this.ecuVariantDescription = ecuVariantDescription;
	}

	public List<ConstantRow> getConstants() {
		return constants;
	}

	public void setConstants(List<ConstantRow> constants) {
		this.constants = constants;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getValidationMessage() {
		return validationMessage;
	}

	public void setValidationMessage(String validationMessage) {
		this.validationMessage = validationMessage;
	}

}

