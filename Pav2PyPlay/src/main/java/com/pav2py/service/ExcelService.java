package com.pav2py.service;


import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pav2py.response.ExcelResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class ExcelService {

	@Autowired
	private ExcelResponse excelResponse;
	
	public ExcelResponse readExcelFile(File file) {
		Workbook workbook=null;
		try {
			System.out.println("name of the file "+file.getName());
			FileInputStream nvm = new FileInputStream(file);
			System.out.println(nvm.toString());
			workbook = new XSSFWorkbook(nvm);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println(e.getMessage());
			excelResponse.setResponseWorkbook(null);
		} 
		excelResponse.setResponseWorkbook(workbook);
		return excelResponse;
	}
}
