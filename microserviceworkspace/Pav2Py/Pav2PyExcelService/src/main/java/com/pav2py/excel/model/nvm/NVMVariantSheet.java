package com.pav2py.excel.model.nvm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;

import com.pav2py.excel.model.abstraction.AbstractSheet;
import com.pav2py.excel.utlilty.Constants;
import com.pav2py.excel.utlilty.Util;

public class NVMVariantSheet extends AbstractSheet{

	private ConstantRow containerConstant;

	private NVMVariantRow selectedVaraint;
	private final DataFormatter formatter = new DataFormatter();

	public NVMVariantSheet(String name, Sheet sheet) {
		super(name, sheet);
	}

	public ConstantRow getContainerConstant() {
		return containerConstant;
	}

	public void setContainerConstant(ConstantRow containerConstant) {
		this.containerConstant = containerConstant;
	}

	public void setSelectedVaraint(NVMVariantRow selectedVaraint) {
		this.selectedVaraint = selectedVaraint;
	}

	@Override
	public boolean validate() {
		boolean compareValuesSection = false;
		boolean SW_ContainerSection = false;
		Sheet sheet = super.getSheet();

		if (sheet.getRow(0).getCell(0).toString().equals(Constants.NVMConstantHeader) && sheet.getRow(0).getCell(1).toString().equals(Constants.NVMDescriptionHeader)
				&& sheet.getRow(0).getCell(2).toString().equals(Constants.NVMECU_ValuesHeader)
				&& sheet.getRow(1).getCell(0).toString().equals(Constants.NVMBoschnumberHeader) && sheet.getRow(2).getCell(0).toString().equals(Constants.NVMDUT_Variant_NumberHeader) 
				&& sheet.getRow(3).getCell(0).toString().equals(Constants.NVMDUT_Variant_DescriptionHeader) && sheet.getRow(4).getCell(0).toString().equals(Constants.NVMPTSHeader)) {
			int compare_value_index = -1;
			int SW_container_index = -1;
			for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {

				//check if compare value header present in cell 0 or 1
				compare_value_index = sheet.getRow(i).getCell(0,Constants.isRowNull) != null? 0 : -1;
				compare_value_index = compare_value_index == -1 ? (sheet.getRow(i).getCell(1,Constants.isRowNull) != null? 1 : -1) : compare_value_index;

				//check if SW_container present in cell 0 or 1
				SW_container_index = sheet.getRow(i).getCell(0,Constants.isRowNull) != null? 0 : -1;
				SW_container_index = SW_container_index == -1 ? (sheet.getRow(i).getCell(1,Constants.isRowNull) != null? 1 : -1) : SW_container_index;

				if(compare_value_index != -1){
					if (sheet.getRow(i).getCell(compare_value_index).toString().equals(Constants.NVMCompare_ValuesHeader)) {
						compareValuesSection = true;
					}
				}

				if (SW_container_index != -1) {
					if (sheet.getRow(i).getCell(SW_container_index).toString().equals(Constants.NVMCompare_ValuesHeader)) {
						SW_ContainerSection = true;
					}
				}
			}
			if (compareValuesSection && SW_ContainerSection) {
				super.setValid(true);
				//super.getParent().setSelectedVaraint();
			}
		}
		if (!super.isValid()) {
			super.setValidationMessage("sheet validation failed ");
		}

		return super.isValid();
	}

	@Override
	public void createRows() {
		HashMap<Integer, Boolean> validatedConstants = new HashMap<>();
		NVMVariantRow nvmVariantRow;
		for (int columnCount = 2; columnCount < super.getSheet().getRow(1).getPhysicalNumberOfCells(); columnCount++) {
			nvmVariantRow = new NVMVariantRow();
			List<ConstantRow> constantRowList = new ArrayList<>();
			//checking for existance of duplicate boschNumber
			if (!checkForFilterData(columnCount)) {
				nvmVariantRow.setBoschNumber(super.getSheet().getRow(1).getCell(columnCount).toString());
				nvmVariantRow.setEcuVariant(Integer.parseInt(formatter.formatCellValue(super.getSheet().getRow(2).getCell(columnCount))));

				nvmVariantRow.setEcuVariantDescription(super.getSheet().getRow(3).getCell(columnCount).toString());
				nvmVariantRow.setPts(super.getSheet().getRow(4).getCell(columnCount).toString());
				//PROCESSING CONSTANTS 
				processConstants(validatedConstants, nvmVariantRow, constantRowList, columnCount);
			}else{
				//MasterLogger.getInstance().logError("Skipping " + (columnCount + 1) + "th column from the sheet  " + Constants.NVM_VARIANTSHEET_NAME + " : Duplicate entry of BoschNumber ");
			}

		}

	}

	private boolean checkForFilterData(int columnCount) {
		boolean isBochNumberPresent = false;
		for (int i = 0; i < super.getRows().size(); i++) {
			NVMVariantRow nvmVariantRow = (NVMVariantRow) super.getRows().get(i);
			if (nvmVariantRow.getBoschNumber().trim().equals(super.getSheet().getRow(1).getCell(columnCount).toString())) {
				isBochNumberPresent = true;
				break;
			}
		}
		return isBochNumberPresent;
	}

	private void processConstants(HashMap<Integer, Boolean> validatedConstants, NVMVariantRow nvmVariantRow, List<ConstantRow> constantRowList, int columnCount) {
		for (int rowCount = 6; rowCount < super.getSheet().getPhysicalNumberOfRows(); rowCount++) {
			if (super.getSheet().getRow(rowCount) != null && super.getSheet().getRow(rowCount).getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) != null) {

				//CHECK IF NOT KEY PRESENT IN MAP : FOR FIRST TIME IT WILL BE TRUE
				if (!validatedConstants.containsKey(rowCount)) {
					if (validateConstantRow(super.getSheet().getRow(rowCount))) {
						validatedConstants.put(rowCount, Boolean.TRUE);
						//invoking and adding createrow function
						constantRowList.add(createConstantRow(super.getSheet().getRow(rowCount), columnCount));
						nvmVariantRow.setConstants(constantRowList);
					} else {
						validatedConstants.put(rowCount, Boolean.FALSE);
						//MasterLogger.getInstance().logError("Skipping " + (rowCount + 1) + "th row from the sheet  " + Constants.NVM_VARIANTSHEET_NAME + " : Either constant is strikeout or not contains \"&c\" or datatype is not present ");
					}
				} else if (validatedConstants.get(rowCount)) {
					constantRowList.add(createConstantRow(super.getSheet().getRow(rowCount), columnCount));
					nvmVariantRow.setConstants(constantRowList);
				}
			}
		}
		super.getRows().add(nvmVariantRow);
	}

	private boolean validateConstantRow(Row row) {
		boolean isValid = false;
		XSSFCellStyle constantStyle = (XSSFCellStyle) row.getCell(0).getCellStyle();
		XSSFFont constantFont = constantStyle.getFont();
		String constantName = row.getCell(0).toString();

		if (constantName.startsWith(Constants.constantStartKey) && !constantFont.getStrikeout()) {
			isValid = true;
		}
		return isValid;

	}


	private ConstantRow createConstantRow(Row row, int columnIndex) {

		ConstantRow constantRow = new ConstantRow();
		String constantProcessor = row.getCell(0).toString();
		String constantName = row.getCell(0).toString();
		String constantDescription = (row.getCell(1,Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) == null)? "" : row.getCell(1).toString();
		String constantValue = (row.getCell(columnIndex,Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) == null)? "" : formatter.formatCellValue(row.getCell(columnIndex));

		constantRow.setName(constantName.substring(1, constantName.length()));
		constantRow.setValue(constantValue);
		constantRow.setDescription(constantDescription);
		constantRow.setDataType(Util.validate(constantProcessor));

		return constantRow;
	}
	@Override
	public List<String> toPy(List<String> pyLines) {
		// TODO Auto-generated method stub
		return null;
	}

	public NVMVariantRow getSelectedVaraint() {
		return selectedVaraint;
	}


}