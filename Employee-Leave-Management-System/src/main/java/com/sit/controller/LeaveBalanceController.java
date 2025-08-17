package com.sit.controller;

import com.sit.entity.LeaveBalance;
import com.sit.service.LeaveBalanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/leave-balance")
public class LeaveBalanceController {

    private final LeaveBalanceService leaveBalanceService;

    public LeaveBalanceController(LeaveBalanceService leaveBalanceService) {
        this.leaveBalanceService = leaveBalanceService;
    }

    // Get leave balance
    @GetMapping("/{employeeId}/{leaveType}")
    public ResponseEntity<?> getLeaveBalance(
            @PathVariable Long employeeId,
            @PathVariable String leaveType) {
        
        Optional<LeaveBalance> balance = leaveBalanceService.getLeaveBalance(employeeId, leaveType);

        return balance.isPresent()
                ? ResponseEntity.ok(balance.get())
                : ResponseEntity.notFound().build();
    }

    // Deduct leave
    @PutMapping("/{employeeId}/{leaveType}/deduct/{days}")
    public ResponseEntity<?> deductLeave(
            @PathVariable Long employeeId,
            @PathVariable String leaveType,
            @PathVariable int days) {
        
        try {
            LeaveBalance updated = leaveBalanceService.updateLeaveBalance(employeeId, leaveType, days);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Create leave balance
    @PostMapping
    public ResponseEntity<LeaveBalance> createLeaveBalance(@RequestBody LeaveBalance leaveBalance) {
        LeaveBalance saved = leaveBalanceService.createLeaveBalance(leaveBalance);
        return ResponseEntity.ok(saved);
    }
}

