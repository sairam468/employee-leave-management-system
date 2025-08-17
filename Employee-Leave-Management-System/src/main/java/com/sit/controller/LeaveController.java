package com.sit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sit.entity.LeaveRequest;
import com.sit.service.LeaveService;

@RestController
@RequestMapping("/leaves")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @PostMapping("/apply")
    public LeaveRequest applyLeave(@RequestBody LeaveRequest leave) {
        return leaveService.applyLeave(leave);
    }

    @PutMapping("/{id}/approve")
    public LeaveRequest approveLeave(@PathVariable Long id,
                                     @RequestParam String managerName,
                                     @RequestParam(required = false) String comments) {
        return leaveService.approveLeave(id, managerName, comments);
    }

    @PutMapping("/{id}/reject")
    public LeaveRequest rejectLeave(@PathVariable Long id,
                                    @RequestParam String managerName,
                                    @RequestParam(required = false) String comments) {
        return leaveService.rejectLeave(id, managerName, comments);
    }

    @GetMapping("/history/{employeeId}")
    public List<LeaveRequest> getLeaveHistory(@PathVariable Long employeeId) {
        return leaveService.getLeaveHistory(employeeId);
    }
    
    @GetMapping("/AllLeaves")
    public List<LeaveRequest> getAllLeaves() {
        return leaveService.getAllLeaves();
    }
    
    @GetMapping("/AllPendingLeaves")
    public List<LeaveRequest> getAllPendingLeaves() {
        return leaveService.getAllPendingLeaves();
    }
}
