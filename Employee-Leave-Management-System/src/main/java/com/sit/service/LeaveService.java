package com.sit.service;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sit.entity.ApprovalHistory;
import com.sit.entity.LeaveBalance;
import com.sit.entity.LeaveRequest;
import com.sit.repository.ApprovalHistoryRepository;
import com.sit.repository.LeaveBalanceRepository;
import com.sit.repository.LeaveRequestRepository;

@Service
public class LeaveService {

    @Autowired
    private LeaveRequestRepository leaveRepo;

    @Autowired
    private LeaveBalanceRepository balanceRepo;

    @Autowired
    private ApprovalHistoryRepository approvalRepo;

    public LeaveRequest applyLeave(LeaveRequest leave) {
        leave.setStatus("PENDING");
        return leaveRepo.save(leave);
    }

    public LeaveRequest approveLeave(Long leaveId, String managerName, String comments) {
        LeaveRequest leave = leaveRepo.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        leave.setStatus("APPROVED");
        leaveRepo.save(leave);

        // Deduct from LeaveBalance
        LeaveBalance balance = balanceRepo.findByEmployeeIdAndLeaveType(
                leave.getEmployeeId(), leave.getLeaveType())
                .orElseThrow(() -> new RuntimeException("Leave balance not found"));

        int days = (int) (leave.getEndDate().toEpochDay() - leave.getStartDate().toEpochDay()) + 1;
        if (balance.getAvailableDays() < days) {
            throw new RuntimeException("Insufficient leave balance");
        }
        balance.setAvailableDays(balance.getAvailableDays() - days);
        balanceRepo.save(balance);

        // Add to ApprovalHistory
        ApprovalHistory history = new ApprovalHistory();
        history.setLeaveRequestId(leaveId);
        history.setApprovedBy(managerName);
        history.setStatus("APPROVED");
        history.setComments(comments);
        history.setTimestamp(LocalDateTime.now());
        approvalRepo.save(history);

        return leave;
    }

    public LeaveRequest rejectLeave(Long leaveId, String managerName, String comments) {
        LeaveRequest leave = leaveRepo.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        leave.setStatus("REJECTED");
        leaveRepo.save(leave);

        ApprovalHistory history = new ApprovalHistory();
        history.setLeaveRequestId(leaveId);
        history.setApprovedBy(managerName);
        history.setStatus("REJECTED");
        history.setComments(comments);
        history.setTimestamp(LocalDateTime.now());
        approvalRepo.save(history);

        return leave;
    }

    public List<LeaveRequest> getLeaveHistory(Long employeeId) {
        return leaveRepo.findByEmployeeId(employeeId);
    }
    
    public List<LeaveRequest> getAllLeaves() {
        return leaveRepo.findAll();
    }
    
    public List<LeaveRequest> getAllPendingLeaves() {
        return leaveRepo.findByStatus("PENDING");
    }
}
