//Copyright (C) Robert Bosch GmbH 2022. All rights reserved.
package com.pav2py.excel.pts.model;

import com.pav2py.excel.model.abstraction.AbstractTermRow;

/**
 *
 * @author PJA7COB
 */
public class PTSVariableRow  extends AbstractTermRow {

    private int size;
    private final StringBuilder pyBuilder = new StringBuilder();
    private int arraySize = -1;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getArraySize() {
        return arraySize;
    }

    public void setArraySize(int arraySize) {
        this.arraySize = arraySize;
    }

    @Override
    public String toPy() {
        pyBuilder.setLength(0);

        switch (getDataType()) {
            case HEX:
            case STRING:
            case NUMBER:
            case INT:
            case i32:
            case ui32:
            case i64:
            case ui64:
            case f64:
            case handle:
            case file:
                return generateNoneVariable();
        }
        return "#" + getName(); //should be a comment
    }

    private String generateNoneVariable() {
        String description = (getDescription() == null || getDescription().isEmpty()) ? "" : getDescription();
        String lvalue = "None";
        pyBuilder.append(getName()).append(" = ").append(lvalue);
        if (!description.isEmpty()) {
            pyBuilder.append("\t#").append(description);
        }
        return pyBuilder.toString();
    }
}
