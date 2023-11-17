package com.pav2py.excel.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pav2py.excel.nvm.service.NVMService;

@RequestMapping("/nvm")
@RestController
public class NVMController {

	@Autowired
	private NVMService nvmService;
	
	@GetMapping("/readExcel")
	public String readExcel(String name,String path) {
		
		String nvmPath="C:\\Users\\WLH1KOR\\Desktop\\SpringWorkspace\\SpringBootLearningProjects\\microserviceworkspace\\Pav2Py\\BASE\\NVM\\NVM_3330BEAT2P_V30.xlsx";
		nvmService.readExcel(nvmPath);
		return "xyzzz";
	}
}
