package com.microservices.hotel.service.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staffs")
public class StaffController {

	@GetMapping("/getallstaffs")
	public ResponseEntity<List<String>> getStaffs(){
		List<String> list = Arrays.asList("Shreedhar","Sunil","Abhishek");
		
		return new ResponseEntity<List<String>>(list,HttpStatus.OK);
	}
}
