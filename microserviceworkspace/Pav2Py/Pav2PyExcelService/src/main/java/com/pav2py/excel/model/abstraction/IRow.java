package com.pav2py.excel.model.abstraction;

public interface IRow {

	String toPy();

    public boolean isValid();

    public void setValid(boolean isValid);

    public String getValidationMessage();

    public void setValidationMessage(String validationMessage);
}
