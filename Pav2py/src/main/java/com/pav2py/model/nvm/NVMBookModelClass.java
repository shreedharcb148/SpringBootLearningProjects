package com.pav2py.model.nvm;


import java.io.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.pav2py.model.abstraction.WorkBookClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NVMBookModelClass extends WorkBookClass{


	private NVMVariantSheetModelClass nvmVariantSheet;

	@Override
	public boolean validate() {
		boolean validFlag = false;
		Sheet nvmSheet = super.getWorkBook().getSheet("NVM");
		if (null != nvmSheet) {
			System.out.println(nvmSheet.getPhysicalNumberOfRows());
		}
		
		return false;
	}

	@Override
	public void createSheets() {
		// TODO Auto-generated method stub		
	}




}
