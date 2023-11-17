//Copyright (C) Robert Bosch GmbH 2022. All rights reserved.
package com.pav2py.excel.pts.model;

import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;

import com.pav2py.excel.model.abstraction.AbstractSheet;
import com.pav2py.excel.utlilty.Constants;
import com.pav2py.excel.utlilty.Util;



/**
 * Holds all the row data from 'GT_Variables' sheet
 *
 * @author PJA7COB
 */
public class VariableSheet extends AbstractSheet {

    public VariableSheet(String name, Sheet sheet) {
        super(name, sheet);
    }

    @Override
    public boolean validate() {
        Row row = super.getSheet().getRow(0);
        if (row.getCell(0).toString().equals(Constants.PTS_VARIABLESHEET_VARIABLE_HEADER)
                && row.getCell(1).toString().equals(Constants.PTS_VARIABLESHEET_ARRAYSIZE_HEADER)
                && row.getCell(2).toString().equals(Constants.PTS_VARIABLESHEET_SIZE_HEADER)
                && row.getCell(3).toString().equals(Constants.PTS_VARIABLESHEET_DESCRIPTION_HEADER)) {
            super.setValid(true);
        }
        return super.isValid();
    }

    @Override
    public void createRows() {
        for (int k = 1; k < super.getSheet().getPhysicalNumberOfRows(); k++) {
            Row row = super.getSheet().getRow(k);
            if (row.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) != null) {
                validateVariableAndAddToPTSVariableRow(row, k);
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

    private void validateVariableAndAddToPTSVariableRow(Row row, int k) {
        boolean isValidVariable = true;
        XSSFCellStyle variableStyle = (XSSFCellStyle) row.getCell(0).getCellStyle();
        XSSFFont variableFont = variableStyle.getFont();

        DataFormatter formatter = new DataFormatter();

        if (row.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) != null) {
            String variableProcessor = row.getCell(0).toString();

            //if (!row.getCell(0).toString().startsWith(Constants.variableStartKey) || variableFont.getStrikeout() || variableProcessor.length < 1 || variableProcessor[0].length() <= 1) {
            if (!variableProcessor.startsWith(Constants.variableStartKey) || variableFont.getStrikeout()) {
                isValidVariable = false;
                //MasterLogger.getInstance().logError("Skipping " + (k + 1) + "th row from the sheet " + Constants.PTSGT_Variables + " : Either constant is strikeout or not contains \"&\" or datatype is not present  ");
            }
            if (isValidVariable) {
                PTSVariableRow ptsVariableRow = new PTSVariableRow();
                ptsVariableRow.setDataType(Util.validate(variableProcessor));

                String variableName = row.getCell(0).toString();
                ptsVariableRow.setName(variableName.substring(1, variableName.length()));

                //add arraysize 
                //check for null/empty in cell
                if (row.getCell(1, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) != null) {
                    int arrSize = Integer.parseInt(formatter.formatCellValue(row.getCell(1)));
                    ptsVariableRow.setArraySize(arrSize);
                }

                //checking for null in value cell
                if (row.getCell(2, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) != null) {
                    int variableValue = Integer.parseInt(formatter.formatCellValue(row.getCell(2)));
                    ptsVariableRow.setSize(variableValue);
                } else {
                    ptsVariableRow.setSize(0);
                }
                if (row.getCell(3, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) != null) {
                    ptsVariableRow.setDescription(row.getCell(3).toString());
                } else {
                    ptsVariableRow.setDescription(null);
                }

                super.getRows().add(ptsVariableRow);

            }
        }else{
             //MasterLogger.getInstance().logError("Skipping " + (k + 1) + "th row from the sheet " + Constants.PTSGT_Variables + " : Variable name is Empty/Null");
        }

    }
}
