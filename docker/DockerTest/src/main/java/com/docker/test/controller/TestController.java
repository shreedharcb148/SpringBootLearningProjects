package com.docker.test.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/getvalues")
	public Map<String,Object> getValues(){
		
		Map<String,Object> data = new HashMap<>();
		
		data.put("message", "api is working");
		data.put("language",Arrays.asList("kannada","english"));
		
		return data;
	}
}
