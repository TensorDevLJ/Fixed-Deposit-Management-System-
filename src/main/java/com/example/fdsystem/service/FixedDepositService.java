package com.example.fdsystem.service;

import com.example.fdsystem.model.FixedDeposit;
import com.example.fdsystem.model.User;
import com.example.fdsystem.repository.FixedDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
public class FixedDepositService {

    @Autowired
    private FixedDepositRepository fdRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private InterestRateService interestRateService;

    public FixedDeposit createFixedDeposit(Long userId, FixedDeposit fdRequest) {
        User user = userService.findById(userId);

        fdRequest.setUser(user);
        fdRequest.setStartDate(LocalDate.now());
        fdRequest.setMaturityDate(LocalDate.now().plusMonths(fdRequest.getTenureMonths()));
        fdRequest.setStatus(FixedDeposit.FDStatus.ACTIVE);

        // Fetch interest rate from configuration based on tenure
        Double configuredRate = interestRateService.getInterestRateForTenure(fdRequest.getTenureMonths());
        fdRequest.setInterestRate(configuredRate);

        BigDecimal principal = fdRequest.getPrincipalAmount();
        double rate = configuredRate / 100.0;
        double timeYears = fdRequest.getTenureMonths() / 12.0;

        // Using simple interest formula for maturity approximation: Amount = P + (P * R
        // * T)
        BigDecimal interest = principal.multiply(BigDecimal.valueOf(rate * timeYears));
        BigDecimal maturityAmount = principal.add(interest).setScale(2, RoundingMode.HALF_UP);

        fdRequest.setMaturityAmount(maturityAmount);

        return fdRepository.save(fdRequest);
    }

    public List<FixedDeposit> getUserDeposits(Long userId) {
        return fdRepository.findByUserId(userId);
    }

    public FixedDeposit closeFixedDeposit(Long fdId) {
        FixedDeposit fd = fdRepository.findById(fdId)
                .orElseThrow(() -> new RuntimeException("FD not found"));

        if (fd.getStatus() != FixedDeposit.FDStatus.ACTIVE) {
            throw new RuntimeException("FD is not active");
        }

        // Check if premature
        if (LocalDate.now().isBefore(fd.getMaturityDate())) {
            fd.setStatus(FixedDeposit.FDStatus.PREMATURE_CLOSED);
            // Penalty logic: reduce interest by 1% for example
            // Recalculate based on actual duration
            // This is a simplified placeholder for penalty logic
        } else {
            fd.setStatus(FixedDeposit.FDStatus.CLOSED);
        }

        return fdRepository.save(fd);
    }
}
