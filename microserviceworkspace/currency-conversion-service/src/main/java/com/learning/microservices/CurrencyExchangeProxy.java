package com.learning.microservices;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//name should be same as currency exchange service name defined in application.properties 
//@FeignClient(name="currency-exchange",url="localhost:8000")

//Load Balancing will happen here automatically - this is client side load balancing
@FeignClient(name="currency-exchange")
public interface CurrencyExchangeProxy  {

	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue(
			@PathVariable("from") String from,
			@PathVariable("to") String to
			) ;
	
	
}
