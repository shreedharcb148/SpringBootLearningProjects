package com.pav2py.excel.model.abstraction;

import org.apache.poi.ss.usermodel.Workbook;

public abstract class AbstractWorkBook {

	private String name;
    private String path;
    private Workbook workbook;
    private String validationMessage;
    private boolean valid;
    
    public AbstractWorkBook(String name, String path, Workbook workbook) {
        this.name = name;
        this.path = path;
        this.workbook = workbook;
    }
   
    public abstract boolean validateAndInit();

    public abstract void createSheets();

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

}

