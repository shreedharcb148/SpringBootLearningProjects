package com.pav2py.excel.model;

import org.springframework.context.annotation.Configuration;

import com.pav2py.excel.model.nvm.NVMBook;

@Configuration
public class MainModel {

	private NVMBook currentNVMBook;

	public NVMBook getCurrentNVMBook() {
		return currentNVMBook;
	}

	public void setCurrentNVMBook(NVMBook currentNVMBook) {
		this.currentNVMBook = currentNVMBook;
	}


}
