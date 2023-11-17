//Copyright (C) Robert Bosch GmbH 2022. All rights reserved.
package com.pav2py.excel.pts.model;


import java.util.ArrayList;
import java.util.HashMap;
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
 * Holds all the row data from 'GT_Numbers' sheet
 *
 * @author PJA7COB
 */
public class PTSVariantSheet extends AbstractSheet {

    private final DataFormatter formatter = new DataFormatter();
    private PTSVariantRow selectedVaraint;

    public PTSVariantSheet(String name, Sheet sheet) {
        super(name, sheet);
    }

    @Override
    public boolean validate() {
        boolean valid = false;
        Sheet sheet = super.getSheet();
        if (sheet.getRow(0).getCell(0).toString().equals(Constants.PTSVariantSheetConstantHeader) 
                && sheet.getRow(0).getCell(1).toString().equals(Constants.PTSVariantSheetDecriptionHeader)
                && sheet.getRow(0).getCell(2).toString().equals(Constants.PTSVariantSheetECU_ValuesHeader)
                && sheet.getRow(1).getCell(0).toString().equals(Constants.PTSVariantSheetECU_Variant_NumberHeader) 
                && sheet.getRow(2).getCell(0).toString().equals(Constants.PTSVariantSheetECU_Variant_DescriptionHeader)) {
            valid = true;
        }
        super.setValid(valid);
        return valid;
    }

    public boolean checkIfNVMVariantExist(int selectedEcuVariant) {
        boolean valid = false;
        Sheet sheet = super.getSheet();
        for (int i = sheet.getRow(1).getPhysicalNumberOfCells() - 1; i > 1; i--) {
            if (sheet.getRow(1).getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) != null) {
                if (Integer.parseInt(formatter.formatCellValue(sheet.getRow(1).getCell(i))) == selectedEcuVariant) {
                    valid = true;
                    break;
                }
            }
        }
        super.setValid(valid);
        return valid;
    }

    @Override
    public void createRows() {
        //add the created row to 'rows' list in super class.
        List<ConstantRow> constantRows;
        HashMap<Integer, Boolean> validatedConstants = new HashMap<>();
        for (int columnIndex = 2; columnIndex < super.getSheet().getRow(1).getPhysicalNumberOfCells(); columnIndex++) {
            PTSVariantRow ptsVariantRow;
            constantRows = new ArrayList<>();
            if (super.getSheet().getRow(1).getCell(columnIndex, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) != null) {
                ptsVariantRow = new PTSVariantRow();
                ptsVariantRow.setEcuVariant(Integer.parseInt(formatter.formatCellValue(super.getSheet().getRow(1).getCell(columnIndex))));

                if (super.getSheet().getRow(2).getCell(columnIndex, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) != null) {
                    ptsVariantRow.setEcuVariantDescription(super.getSheet().getRow(2).getCell(columnIndex).toString());
                }
                //processing all the constants with ecu values column
                processConstants(validatedConstants, ptsVariantRow, constantRows, columnIndex);
            }
        }
    }

   @Override
    public List<String> toPy(List<String> pyLines) {
       addHeader(pyLines);
       if (selectedVaraint.getConstants() != null) {
           selectedVaraint.getConstants().forEach(constant -> {
               pyLines.add(constant.toPy());
           });
       }
       return pyLines;
    }

    private boolean validateConstantRow(Row row) {

        boolean isValid = false;
        XSSFCellStyle constantStyle = (XSSFCellStyle) row.getCell(0).getCellStyle();
        XSSFFont constantFont = constantStyle.getFont();
        String constantName = row.getCell(0).toString();
        if (constantName.startsWith(Constants.constantStartKey) && !constantFont.getStrikeout()) {
            isValid = true;
        } else {
          
        }
        return isValid;
    }

    private ConstantRow createConstantRow(Row row, int columnIndex) {

        ConstantRow constantRow = new ConstantRow();
        String constantProcessor = row.getCell(0).toString();
        String constantName = row.getCell(0).toString();
        String constantDescription = (row.getCell(1,Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) == null)? "" : row.getCell(1).toString();
        String constantValue = (row.getCell(columnIndex,Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) == null)? "" :formatter.formatCellValue(row.getCell(columnIndex));

        constantRow.setName(constantName.substring(1, constantName.length()));
        constantRow.setValue(constantValue);
        constantRow.setDescription(constantDescription);
        constantRow.setDataType(Util.validate(constantProcessor));

        return constantRow;
    }

    public void setSelectVariant(int ecuVatiant) {
        for (int i = super.getRows().size() - 1; i >= 0; i--) {
            PTSVariantRow ptsVariantRow = (PTSVariantRow) super.getRows().get(i);
            if (ptsVariantRow.getEcuVariant() == ecuVatiant) {
                selectedVaraint = ptsVariantRow;
                break;
            }
        }
    }

    public PTSVariantRow getSelectedVariant() {
        return selectedVaraint;
    }

    private void processConstants(HashMap<Integer, Boolean> validatedConstants, PTSVariantRow ptsVariantRow, List<ConstantRow> constantRows, int columnIndex) {

        //HASHMAP IS USED TO AVOID THE DISPLAY OF SAME ERROR MULTIPLE TIMES
        //HASHMAP IS USED TO VALIDATE CONSTANTS ONE TIME
        for (int rowIndex = 3; rowIndex < super.getSheet().getPhysicalNumberOfRows(); rowIndex++) {
            if (super.getSheet().getRow(rowIndex) != null && super.getSheet().getRow(rowIndex).getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) != null) {
                //CHECK IF NOT KEY PRESENT IN MAP : FOR FIRST TIME IT WILL BE TRUE
                if (!validatedConstants.containsKey(rowIndex)) {
                    if (validateConstantRow(super.getSheet().getRow(rowIndex))) {
                        validatedConstants.put(rowIndex, Boolean.TRUE);
                        //invoking and adding createrow function
                        constantRows.add(createConstantRow(super.getSheet().getRow(rowIndex), columnIndex));
                        ptsVariantRow.setConstants(constantRows);
                    } else {
                        validatedConstants.put(rowIndex, Boolean.FALSE);
                       // MasterLogger.getInstance().logError("Skipping " + (rowIndex + 1) + "th row from the sheet  " + Constants.PTSGT_Numbers + " : Either constant is strikeout or not contains \"&c\" or datatype is not present ");
                    }
                } else if (validatedConstants.get(rowIndex)) {
                    constantRows.add(createConstantRow(super.getSheet().getRow(rowIndex), columnIndex));
                    ptsVariantRow.setConstants(constantRows);
                }
            }
        }
        super.getRows().add(ptsVariantRow);

    }

    private void addHeader(List<String> pyLines) {
         //added global variables section
        pyLines.add(Util.addNewLineInWindowsStyle());
        pyLines.add("#global variables");
        pyLines.add("G_UI32_DUT_VARIANT_NUMBER = "+this.getSelectedVariant().getEcuVariant());
        pyLines.add(Util.addNewLineInWindowsStyle());
    }

}
