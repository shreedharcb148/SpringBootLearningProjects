//Copyright (C) Robert Bosch GmbH 2022. All rights reserved.
package com.pav2py.excel.pts.model;

import com.pav2py.excel.model.abstraction.AbstarctTestRow;

/**
 * Model for Chapter row from TestGateSheet
 * 
 * @author PJA7COB
 */
public class ChapterRow extends AbstarctTestRow {
    
    

    private String name;   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toPy() {
        return "#CHAPTER : Position : " + getPosition() + " Name : " + name;
    }

}
