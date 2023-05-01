package com.learning.currencyexchangeservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class CircuitBreakerController {

    @GetMapping("/sample-api")
//    @Retry(name = "sample-api", fallbackMethod = "fallback")
//    @CircuitBreaker(name = "default", fallbackMethod = "fallback")
//    RateLimiter => in 10s 10000 calls to the api
    @RateLimiter(name = "default")
    public String sampleApi() {
        log.info(" ----> Sample api call received.");

//        ResponseEntity<String> entity =
//                new RestTemplate().getForEntity(
//                        "http://localhost:8080/some-dummy-url",
//                        String.class
//                );

//        return entity.getBody();

        return "sample-api";
    }

    public String fallback(Exception ex) {
        return "fallback";
    }
}
