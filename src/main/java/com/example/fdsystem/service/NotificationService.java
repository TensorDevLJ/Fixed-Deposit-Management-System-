package com.example.fdsystem.service;

import com.example.fdsystem.model.FixedDeposit;
import com.example.fdsystem.repository.FixedDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private FixedDepositRepository fdRepository;

    // Run every day at 9 AM
    @Scheduled(cron = "0 0 9 * * ?")
    public void sendMaturityNotifications() {
        LocalDate today = LocalDate.now();
        List<FixedDeposit> allDeposits = fdRepository.findAll();

        for (FixedDeposit fd : allDeposits) {
            if (fd.getStatus() == FixedDeposit.FDStatus.ACTIVE &&
                    fd.getMaturityDate().equals(today)) {

                System.out.println("NOTIFICATION: Dear " + fd.getUser().getFullName() +
                        ", your FD with ID " + fd.getId() + " matures today. Amount: " + fd.getMaturityAmount());
                // In real app, call EmailService or SMSService here
            }
        }
    }
}
