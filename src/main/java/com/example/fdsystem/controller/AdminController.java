package com.example.fdsystem.controller;

import com.example.fdsystem.model.InterestRateConfig;
import com.example.fdsystem.service.InterestRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private InterestRateService interestRateService;

    @PostMapping("/rates")
    public ResponseEntity<InterestRateConfig> addRate(@RequestBody InterestRateConfig config) {
        return ResponseEntity.ok(interestRateService.addInterestRate(config));
    }

    @GetMapping("/rates")
    public ResponseEntity<List<InterestRateConfig>> getAllRates() {
        return ResponseEntity.ok(interestRateService.getAllRates());
    }
}
