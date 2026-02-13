package com.example.fdsystem.repository;

import com.example.fdsystem.model.InterestRateConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestRateConfigRepository extends JpaRepository<InterestRateConfig, Long> {
    // Find rate for a specific tenure
    // This might need a custom query or logic in service if ranges overlap or are
    // complex
}
