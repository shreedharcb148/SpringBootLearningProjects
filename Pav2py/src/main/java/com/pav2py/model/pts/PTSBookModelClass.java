package com.pav2py.model.pts;

import java.io.*;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.pav2py.model.abstraction.WorkBookClass;

public class PTSBookModelClass extends WorkBookClass{

	@Override
	public boolean validate() {
		return false;
	}

	@Override
	public void createSheets() {
		// TODO Auto-generated method stub
		
	}

}

