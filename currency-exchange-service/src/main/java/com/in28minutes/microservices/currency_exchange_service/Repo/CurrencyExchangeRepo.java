package com.in28minutes.microservices.currency_exchange_service.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in28minutes.microservices.currency_exchange_service.bean.CurrencyExchange;
import java.util.List;


public interface CurrencyExchangeRepo extends JpaRepository<CurrencyExchange, Long>{
	CurrencyExchange findByFromAndTo(String from, String to);
}
