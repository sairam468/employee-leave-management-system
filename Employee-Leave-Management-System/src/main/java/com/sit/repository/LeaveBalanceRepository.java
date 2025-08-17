package com.sit.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sit.entity.LeaveBalance;

public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance, Long> {

    // Find leave balance for a given employee & leave type
    Optional<LeaveBalance> findByEmployeeIdAndLeaveType(Long employeeId, String leaveType);

    // Optional: Find all leave balances of an employee
    List<LeaveBalance> findByEmployeeId(Long employeeId);
}
	