package com.learning.currencyexchangeservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class CircuitBreakerController {

    @GetMapping("/sample-api")
    public String sampleApi() {
        log.info(" ----> Sample api call received.");

        ResponseEntity<String> entity =
                new RestTemplate().getForEntity(
                        "http://localhost:8080/some-dummy-url",
                        String.class
                );

        return entity.getBody();
    }
}
