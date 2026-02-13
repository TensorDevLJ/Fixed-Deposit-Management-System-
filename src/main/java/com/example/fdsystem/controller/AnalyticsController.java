package com.example.fdsystem.controller;

import com.example.fdsystem.model.FixedDeposit;
import com.example.fdsystem.repository.FixedDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    @Autowired
    private FixedDepositRepository fdRepository;

    @GetMapping("/summary")
    public ResponseEntity<Map<String, Object>> getSummary() {
        Map<String, Object> summary = new HashMap<>();

        long activeCount = fdRepository.countByStatus(FixedDeposit.FDStatus.ACTIVE);
        BigDecimal totalActiveAmount = fdRepository.sumActivePrincipalAmount();

        summary.put("activeFds", activeCount);
        summary.put("totalActivePrincipal", totalActiveAmount != null ? totalActiveAmount : BigDecimal.ZERO);

        return ResponseEntity.ok(summary);
    }
}
