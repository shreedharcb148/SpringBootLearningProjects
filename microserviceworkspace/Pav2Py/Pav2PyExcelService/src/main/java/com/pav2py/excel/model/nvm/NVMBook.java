package com.pav2py.excel.model.nvm;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.pav2py.excel.model.abstraction.AbstractWorkBook;
import com.pav2py.excel.utlilty.Constants;

public class NVMBook extends AbstractWorkBook{

	private NVMVariantSheet variantSheet;

	public NVMBook(String name, String path, Workbook workbook) {
		super(name, path, workbook);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validateAndInit() {
		boolean validFlag = false;
		Sheet nvmSheet = super.getWorkbook().getSheet("NVM");
		if (null != nvmSheet) {
			variantSheet = new NVMVariantSheet("NVM", nvmSheet);
			if (variantSheet.validate()) {
				validFlag = true;
			}
		}
		if (!validFlag) {
			super.setValidationMessage("Either sheet not found or not in required format");
		}
		super.setValid(validFlag);

		return validFlag;
	}

	@Override
	public void createSheets() {
		// TODO Auto-generated method stub

	}

	public NVMVariantSheet getVariantSheet() {
		return variantSheet;
	}

	public void setVariantSheet(NVMVariantSheet variantSheet) {
		this.variantSheet = variantSheet;
	}

	
	
}
