package com.example.fdsystem.repository;

import com.example.fdsystem.model.FixedDeposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FixedDepositRepository extends JpaRepository<FixedDeposit, Long> {
    List<FixedDeposit> findByUserId(Long userId);

    long countByStatus(FixedDeposit.FDStatus status);

    @org.springframework.data.jpa.repository.Query("SELECT SUM(f.principalAmount) FROM FixedDeposit f WHERE f.status = 'ACTIVE'")
    java.math.BigDecimal sumActivePrincipalAmount();
}
