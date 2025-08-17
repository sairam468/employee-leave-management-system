package com.sit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sit.entity.LeaveRequest;
import com.sit.service.ReportService;

@RestController
@RequestMapping("/getLeaves")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // GET /leaves/reports?status=APPROVED
    @GetMapping("/reports")
    public ResponseEntity<List<LeaveRequest>> getLeaveReports(
            @RequestParam(required = false) String status) {

        List<LeaveRequest> leaves;

        if (status == null) {
            leaves = reportService.getAllLeaves();
        } else {
            switch (status.toUpperCase()) {
                case "APPROVED":
                    leaves = reportService.getApprovedLeaves();
                    break;
                case "REJECTED":
                    leaves = reportService.getRejectedLeaves();
                    break;
                case "PENDING":
                    leaves = reportService.getPendingLeaves();
                    break;
                default:
                    leaves = reportService.getAllLeaves(); // fallback to all
                    break;
            }
        }

        if (leaves.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(leaves);
    }
}
