package com.example.fdsystem.service;

import com.example.fdsystem.model.InterestRateConfig;
import com.example.fdsystem.repository.InterestRateConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestRateService {

    @Autowired
    private InterestRateConfigRepository rateRepository;

    public InterestRateConfig addInterestRate(InterestRateConfig config) {
        return rateRepository.save(config);
    }

    public List<InterestRateConfig> getAllRates() {
        return rateRepository.findAll();
    }

    public Double getInterestRateForTenure(Integer tenureMonths) {
        return rateRepository.findAll().stream()
                .filter(rate -> tenureMonths >= rate.getMinTenureMonths() && tenureMonths <= rate.getMaxTenureMonths())
                .findFirst()
                .map(InterestRateConfig::getInterestRate)
                .orElse(5.0); // Default rate if no config found
    }
}
