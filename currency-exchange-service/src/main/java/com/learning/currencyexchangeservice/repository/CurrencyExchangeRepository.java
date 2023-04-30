package com.learning.currencyexchangeservice.repository;

import com.learning.currencyexchangeservice.model.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {

    CurrencyExchange findByFromAndTo(String from, String to);
}
