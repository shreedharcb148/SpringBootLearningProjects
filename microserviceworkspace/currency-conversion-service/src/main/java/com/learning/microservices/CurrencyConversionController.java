package com.learning.microservices;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("currency-conversion")
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyExchangeProxy currencyExchangeProxy;
	
	@GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(
			@PathVariable("from") String from,
			@PathVariable("to") String to,
			@PathVariable("quantity") BigDecimal quantity
			) {
		
		HashMap<String,String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().
				getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
				CurrencyConversion.class,
				uriVariables );
		
		CurrencyConversion cc = responseEntity.getBody();
		
		return new CurrencyConversion(cc.getId(),from,to,quantity,
				cc.getConversionMultiple(),
				quantity.multiply(cc.getConversionMultiple()),
				cc.getEnvironment());
	}
	
	@GetMapping("/feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionFeign(
			@PathVariable("from") String from,
			@PathVariable("to") String to,
			@PathVariable("quantity") BigDecimal quantity
			) {
		
		HashMap<String,String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		CurrencyConversion cc = currencyExchangeProxy.retrieveExchangeValue(from, to);
		
		return new CurrencyConversion(cc.getId(),from,to,quantity,
				cc.getConversionMultiple(),
				quantity.multiply(cc.getConversionMultiple()),
				cc.getEnvironment());
	}
	
	
}
