package com.pav2py.excel.model.abstraction;

import com.pav2py.excel.utlilty.EDataType;

public abstract class AbstractTermRow implements IRow {

    
    private String name;
    private String description;
    private EDataType dataType;
    private boolean valid;
    private String validationMessage;

    public abstract String toPy();

    public void setName(String name) {
        //derive the dataType and call setDataType(DataType dataType)
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EDataType getDataType() {
        return dataType;
    }

    public void setDataType(EDataType dataType) {
        this.dataType = dataType;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean isValid) {
        this.valid = isValid;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }

}
