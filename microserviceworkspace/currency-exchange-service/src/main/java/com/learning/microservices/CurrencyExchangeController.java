package com.learning.microservices;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("currency-exchange")
public class CurrencyExchangeController {

	@Autowired
	private Environment environment;

	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepository;

	@GetMapping("/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(
			@PathVariable("from") String from,
			@PathVariable("to") String to
			) {

		String port = environment.getProperty("local.server.port");
		//CurrencyExchange currencyExchange = new CurrencyExchange(1000L,from,to,BigDecimal.valueOf(50));

		CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);
		if(currencyExchange == null) {
			throw new RuntimeException("Unable to find the data for "+from + "to "+to);
		}
		currencyExchange.setEnvironment(port);
		return currencyExchange;
	}
}
