package com.pav2py.service.nvm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.*;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.pav2py.model.nvm.NVMBookModelClass;
import com.pav2py.service.abstraction.NVMService;

public class NVMBookServiceClass extends NVMService {

	@Autowired
	private NVMBookModelClass nvmBookModelClass;
	

	@Override
	public boolean validate() {
		
		//path need to pass here
		File nvmFile = new File("");
		try(FileInputStream fileInputStream = new FileInputStream(nvmFile)){
			try(Workbook workbook = new XSSFWorkbook(new FileInputStream(""))){
				
				//set fields to nvmBookModelClass
				nvmBookModelClass.setWorkBook(workbook);
				nvmBookModelClass.setName(nvmFile.getName());
				nvmBookModelClass.setPath(nvmFile.getPath());
				
				System.out.println(nvmBookModelClass.validate());

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return false;
	}

	@Override
	public void createSheets() {
		// TODO Auto-generated method stub

	}

}
