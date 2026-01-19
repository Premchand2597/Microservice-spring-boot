package com.in28minutes.microservices.currency_conversion_service_1.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.in28minutes.microservices.currency_conversion_service_1.bean.CurrencyConversion;


@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyExchangeProxy proxy;

	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{q}")
	public CurrencyConversion retrieveData(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal q) {
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		ResponseEntity<CurrencyConversion> fetchedData = new RestTemplate().
				getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);
		
		CurrencyConversion conversion = fetchedData.getBody();
		
		return new CurrencyConversion(conversion.getId(), from, to, conversion.getConversionMultiple(), q, 
							q.multiply(conversion.getConversionMultiple()), conversion.getEnvironment()+" rest template");
	}
	
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{q}")
	public CurrencyConversion retrieveDataFromFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal q) {
		
		CurrencyConversion conversion = proxy.retrieveData(from, to);
		
		return new CurrencyConversion(conversion.getId(), from, to, conversion.getConversionMultiple(), q, 
							q.multiply(conversion.getConversionMultiple()), conversion.getEnvironment()+" feign");
	}
}