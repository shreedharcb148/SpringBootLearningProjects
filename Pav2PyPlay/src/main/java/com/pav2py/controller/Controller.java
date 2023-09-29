package com.pav2py.controller;

import java.io.File;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pav2py.response.ExcelResponse;
import com.pav2py.service.ExcelService;

@RestController
@RequestMapping("/pav2py")
//@ComponentScan(basePackages = "com.pav2py.response")
public class Controller {

	@Autowired
	private ExcelService excelService;
	
	@GetMapping("/read")
	public int readExcelFile(@RequestParam("path") String filePath) {
		
		File file = new File("C:\\Users\\WLH1KOR\\Desktop\\Shreedhar\\BASE\\NVM\\10_NVM_ExcelBEAT\\NVM_3330BEAT2P_V30.xlsx");
		ExcelResponse excelResponse = excelService.readExcelFile(file);
		return excelResponse.getResponseWorkbook().getNumberOfSheets();
	}
}
