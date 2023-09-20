package com.pav2py.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pav2py.model.nvm.NVMBookModelClass;

@RestController
@RequestMapping("/nvm")
public class NVMController {

	
	private NVMBookModelClass nvmBook;
	
	public NVMController() {
		super();
	}

	@Autowired
	public NVMController(NVMBookModelClass nvmBook) {
		this.nvmBook = nvmBook;
	}
	
	@GetMapping("/readnvm")
	public NVMBookModelClass readNVMWorkBook() {
		
		return null;
		
	}
	
	@GetMapping("/validate")
	public boolean validateNVMWorkBook(@PathVariable String nvmfile) {
		
		return true;
		
	}
	public void createNVMSheetRows() {
		
	}
}
