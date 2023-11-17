package com.pav2py.excel.model.abstraction;

public abstract class AbstarctTestRow implements IRow{

	private int position;
    private boolean valid;
    private String validationMessage;

   
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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

