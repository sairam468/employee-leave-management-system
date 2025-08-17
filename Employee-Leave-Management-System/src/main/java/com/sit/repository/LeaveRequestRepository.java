package com.sit.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sit.entity.LeaveRequest;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    // Custom query method - Spring will implement automatically
    List<LeaveRequest> findByEmployeeId(Long employeeId);

    // Also useful for HR reports
    List<LeaveRequest> findByStatus(String status);
}
