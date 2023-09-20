package com.pav2py.model.abstraction;

import org.apache.poi.ss.usermodel.Workbook;

public abstract class WorkBookClass {

	public String name;
	
	public String path;
	
	public Workbook workBook;
	
	public abstract boolean validate();
	
	public abstract void createSheets();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Workbook getWorkBook() {
		return workBook;
	}

	public void setWorkBook(Workbook workBook) {
		this.workBook = workBook;
	}

	
	
	

}
