package com.sit.service;

import com.sit.entity.LeaveBalance;
import com.sit.repository.LeaveBalanceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LeaveBalanceService {

    private final LeaveBalanceRepository leaveBalanceRepository;

    public LeaveBalanceService(LeaveBalanceRepository leaveBalanceRepository) {
        this.leaveBalanceRepository = leaveBalanceRepository;
    }

    // Get leave balance for an employee by leave type
    public Optional<LeaveBalance> getLeaveBalance(Long employeeId, String leaveType) {
        return leaveBalanceRepository.findByEmployeeIdAndLeaveType(employeeId, leaveType);
    }

    // Update leave balance after deduction
    public LeaveBalance updateLeaveBalance(Long employeeId, String leaveType, int daysToDeduct) {
        Optional<LeaveBalance> balanceOpt = leaveBalanceRepository.findByEmployeeIdAndLeaveType(employeeId, leaveType);

        if (balanceOpt.isPresent()) {
            LeaveBalance balance = balanceOpt.get();
            int remaining = balance.getAvailableDays() - daysToDeduct;

            if (remaining < 0) {
                throw new RuntimeException("Not enough leave balance!");
            }

            balance.setAvailableDays(remaining);
            return leaveBalanceRepository.save(balance);
        } else {
            throw new RuntimeException("Leave balance not found for employeeId: " + employeeId);
        }
    }

    // Save new leave balance
    public LeaveBalance createLeaveBalance(LeaveBalance leaveBalance) {
        return leaveBalanceRepository.save(leaveBalance);
    }
}
