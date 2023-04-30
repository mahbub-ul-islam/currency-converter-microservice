package com.learning.limitservice.controller;

import com.learning.limitservice.config.Configaration;
import com.learning.limitservice.model.Limits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LimitsController {

    @Autowired
    private Configaration configaration;

    @GetMapping("/limits")
    public Limits retriveLimits() {
//        return new Limits(1, 1000);
        return new Limits(configaration.getMinimum(), configaration.getMaximum());
    }
}
