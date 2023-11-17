//Copyright (C) Robert Bosch GmbH 2022. All rights reserved.
package com.pav2py.excel.pts.model;

import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;

import com.pav2py.excel.model.abstraction.AbstractSheet;
import com.pav2py.excel.model.nvm.ConstantRow;
import com.pav2py.excel.utlilty.Constants;
import com.pav2py.excel.utlilty.Util;

/**
 * Holds all the row data from 'GT_Constants' sheet
 *
 * @author PJA7COB
 */
public class ConstantSheet extends AbstractSheet {

    public ConstantSheet(String name, Sheet sheet) {
        super(name, sheet);
    }

    @Override
    public boolean validate() {
        Row row = super.getSheet().getRow(0);
        if (row.getCell(0).toString().equals(Constants.PTSConstantSheetConstantHeader) && row.getCell(1).toString().equals(Constants.PTSConstantSheetValueHeader) && row.getCell(2).toString().equals(Constants.PTSConstantSheetDescriptionHeader)) {
            super.setValid(true);
        }
        return super.isValid();
    }

    @Override
    public void createRows() {
        for (int k = 1; k < super.getSheet().getPhysicalNumberOfRows(); k++) {
            if (super.getSheet().getRow(k) != null) {
                Row row = super.getSheet().getRow(k);
                if (row.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) != null) {
                    validateConstantandAddToConstantRow(row, k);
                }
            }
        }
    }

    @Override
    public List<String> toPy(List<String> pyLines) {
        
        if (getRows() != null) {
            getRows().forEach(rows -> {
                pyLines.add(rows.toPy());
            });
        }
        return pyLines;
    }

    private void validateConstantandAddToConstantRow(Row row, int k) {

        boolean isValidConstant = true;
        XSSFCellStyle constantStyle = (XSSFCellStyle) row.getCell(0).getCellStyle();
        XSSFFont constantFont = constantStyle.getFont();

        DataFormatter formatter = new DataFormatter();

        if (row.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) != null) {
            String constantProcessor = row.getCell(0).toString();
            if (!row.getCell(0).toString().startsWith(Constants.constantStartKey) || constantFont.getStrikeout()) {
                isValidConstant = false;
                //MasterLogger.getInstance().logError("Skipping " + (k + 1) + "th row from the sheet  " + Constants.PTSGT_Constants + " : Either constant is strikeout or not contains \"&c\" or datatype is not present ");

            }
            if (isValidConstant) {
                //check for non empty value
                if (row.getCell(1, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) != null) {
                    ConstantRow ptsConstantRow = new ConstantRow();
                    //String constant = constantProcessor[0];
                    //String constantType = constant.substring(2, constant.length());

                    String constantName = row.getCell(0).toString();
                    String constantValue = formatter.formatCellValue(row.getCell(1));
                    String constantDescription = (row.getCell(2,Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) == null)? "" : row.getCell(2).toString();

                    ptsConstantRow.setName(constantName.substring(1, constantName.length()));
                    ptsConstantRow.setDataType(Util.validate(constantProcessor));
                    ptsConstantRow.setValue(constantValue);
                    

                    //if description is null consider it as as empty
                    if (row.getCell(2, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) != null) {
                        ptsConstantRow.setDescription(constantDescription);
                    } else {
                        ptsConstantRow.setDescription("");
                    }
                    

                    //duplicate entry for constants
                    if (!super.getRows().contains(ptsConstantRow.getName().equals(constantName))) { 
                        super.getRows().add(ptsConstantRow);
                    } else {
                        //MasterLogger.getInstance().logWarning("Duplicate entry of constant !" + ptsConstantRow.getName());
                    }
                } else {
                    //MasterLogger.getInstance().logError("Skipping " + (k + 1) + "th row from the sheet " + Constants.PTSGT_Constants + " : Value is Empty/Null");
                }

            }
        }else{
			//MasterLogger.getInstance().logError("Skipping " + (k + 1) + "th row from the sheet " + Constants.PTSGT_Constants + " : Constant name is Empty/Null");
        }

    }

}
