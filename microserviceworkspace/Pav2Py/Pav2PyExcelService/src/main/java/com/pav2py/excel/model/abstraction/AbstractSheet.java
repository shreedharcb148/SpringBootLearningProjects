package com.pav2py.excel.model.abstraction;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

public abstract class AbstractSheet {

	//Name of this object, can be a sheet name or other name, depends on the input
    private String name;
    private Sheet sheet;
    private List<IRow> rows;
    private boolean valid;
    private String validationMessage;

    public AbstractSheet(String name, Sheet sheet) {
        this.name = name;
        this.sheet = sheet;
        this.rows = new ArrayList<>();
    }

    public abstract boolean validate();

    public abstract void createRows();

    public abstract List<String> toPy(List<String> pyLines);

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public List<IRow> getRows() {
        return rows;
    }

    public String getName() {
        return name;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }

    public Sheet getSheet() {
        return sheet;
    }

}

