package com.example.fdsystem.controller;

import com.example.fdsystem.model.FixedDeposit;
import com.example.fdsystem.service.FixedDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fds")
public class FixedDepositController {

    @Autowired
    private FixedDepositService fdService;

    @PostMapping("/{userId}")
    public ResponseEntity<FixedDeposit> createFD(@PathVariable Long userId, @RequestBody FixedDeposit fdRequest) {
        return ResponseEntity.ok(fdService.createFixedDeposit(userId, fdRequest));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<FixedDeposit>> getUserFDs(@PathVariable Long userId) {
        return ResponseEntity.ok(fdService.getUserDeposits(userId));
    }

    @PostMapping("/{fdId}/close")
    public ResponseEntity<FixedDeposit> closeFD(@PathVariable Long fdId) {
        return ResponseEntity.ok(fdService.closeFixedDeposit(fdId));
    }
}
