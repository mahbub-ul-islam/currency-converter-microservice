package com.learning.currencyconversionservice.controller;

import com.learning.currencyconversionservice.model.CurrencyConversion;
import com.learning.currencyconversionservice.proxy.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
//@RequestMapping("/currency-conversion")
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy proxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ) {

        HashMap<String, String> uriVariable = new HashMap<>();
        uriVariable.put("from", from);
        uriVariable.put("to", to);

        ResponseEntity<CurrencyConversion> entity =
                new RestTemplate().getForEntity(
                        "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                        CurrencyConversion.class,
                        uriVariable);

        CurrencyConversion currencyConversion = entity.getBody();

        return new CurrencyConversion(
                currencyConversion.getId(),
                currencyConversion.getFrom(),
                currencyConversion.getTo(),
                currencyConversion.getConversionMultiple(),
                quantity,
                quantity.multiply(currencyConversion.getConversionMultiple()),
                currencyConversion.getEnvironment() + " Rest template"
        );
    }


    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ) {

        CurrencyConversion currencyConversion = proxy.retrieveExchangeValue(from, to);

        return new CurrencyConversion(
                currencyConversion.getId(),
                currencyConversion.getFrom(),
                currencyConversion.getTo(),
                currencyConversion.getConversionMultiple(),
                quantity,
                quantity.multiply(currencyConversion.getConversionMultiple()),
                currencyConversion.getEnvironment() + " feign"
        );
    }
}
