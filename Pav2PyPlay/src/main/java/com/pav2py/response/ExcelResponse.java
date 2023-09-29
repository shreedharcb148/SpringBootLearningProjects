package com.pav2py.response;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.context.annotation.Bean;
public class ExcelResponse {

	private String responseData;
	
	private String responseMessage;
	
	private int responseCode;
	
	private Workbook responseWorkbook;

	public ExcelResponse(String responseData, String responseMessage, int responseCode, Workbook responseWorkbook) {
		super();
		this.responseData = responseData;
		this.responseMessage = responseMessage;
		this.responseCode = responseCode;
		this.responseWorkbook = responseWorkbook;
	}

	public String getResponseData() {
		return responseData;
	}

	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public Workbook getResponseWorkbook() {
		return responseWorkbook;
	}

	public void setResponseWorkbook(Workbook responseWorkbook) {
		this.responseWorkbook = responseWorkbook;
	}
	
	
	
}
