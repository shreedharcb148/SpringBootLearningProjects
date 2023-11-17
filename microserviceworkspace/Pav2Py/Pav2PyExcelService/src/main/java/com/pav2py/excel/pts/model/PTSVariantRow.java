//Copyright (C) Robert Bosch GmbH 2022. All rights reserved.
package com.pav2py.excel.pts.model;

import com.pav2py.excel.model.abstraction.AbstractVariantRow;

/**
 *
 * @author PJA7COB
 */
public class PTSVariantRow extends AbstractVariantRow {

    @Override
    public String toPy() {
        //We dont need to handle toPy here. Because selected ariant will have all the required
        //details. So the pyLines conversion will happen in PTSVariantSheet itself.
        return "";
    }

}
