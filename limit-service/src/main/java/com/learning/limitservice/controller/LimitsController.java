package com.learning.limitservice.controller;

import com.learning.limitservice.model.Limits;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LimitsController {

    @GetMapping("/limits")
    public Limits retriveLimits() {
        return new Limits(1, 1000);
    }
}
