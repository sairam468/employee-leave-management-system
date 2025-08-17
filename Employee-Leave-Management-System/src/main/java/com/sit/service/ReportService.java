package com.sit.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sit.entity.LeaveRequest;
import com.sit.repository.LeaveRequestRepository;

@Service
public class ReportService {

    @Autowired
    private LeaveRequestRepository leaveRepo;

    public List<LeaveRequest> getAllLeaves() {
        return leaveRepo.findAll();
    }

    public List<LeaveRequest> getApprovedLeaves() {
        return leaveRepo.findByStatus("APPROVED");
    }

    public List<LeaveRequest> getRejectedLeaves() {
        return leaveRepo.findByStatus("REJECTED");
    }

    public List<LeaveRequest> getPendingLeaves() {
        return leaveRepo.findByStatus("PENDING");
    }
}
