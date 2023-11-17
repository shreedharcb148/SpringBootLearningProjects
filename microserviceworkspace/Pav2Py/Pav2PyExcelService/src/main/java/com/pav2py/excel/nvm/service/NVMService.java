package com.pav2py.excel.nvm.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pav2py.excel.model.MainModel;
import com.pav2py.excel.model.nvm.*;



@Service
public class NVMService {

	@Autowired
	private MainModel mainModel;
	
	Workbook nvmWorkbook;
	NVMBook nvmBook;
	File file;
	
	NVMVariantSheet nvmVariantSheet;
	
	
	public NVMVariantSheet readExcel(String nvmFilePath) {
		
		file = new File(nvmFilePath);
		try {
			
			FileInputStream inputStream = new FileInputStream(file);
			
			//read workbook using stream
		    nvmWorkbook = new XSSFWorkbook(inputStream);
		    System.out.println("----- "+file.getName()+" :: "+file.getPath());
			//create NVMBook object
			nvmBook = new NVMBook(file.getName(), file.getPath(), nvmWorkbook);
			
			System.out.println(nvmBook.getName());
			
			if(nvmBook.validateAndInit()) {
				System.out.println("validation sucess");
				 nvmVariantSheet = nvmBook.getVariantSheet();
                 nvmVariantSheet.createRows();
                 mainModel.setCurrentNVMBook(nvmBook);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return nvmVariantSheet;
		
	}

	
}
