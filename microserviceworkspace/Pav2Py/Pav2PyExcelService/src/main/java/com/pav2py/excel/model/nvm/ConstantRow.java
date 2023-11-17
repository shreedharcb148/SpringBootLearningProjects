package com.pav2py.excel.model.nvm;

import com.pav2py.excel.model.abstraction.AbstractTermRow;

public class ConstantRow  extends AbstractTermRow {

	private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
	@Override
	public String toPy() {
		// TODO Auto-generated method stub
		return null;
	}

}
