package com.sit.entity;

import java.time.LocalDate;

import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Entity
@Data
public class LeaveRequest {

	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;

	  private Long employeeId;
      private String leaveType;  // Casual, Sick (or) Earned
      private LocalDate startDate;
      private LocalDate endDate;
      private String status;     // Pending, Approved (or) Rejected
      private String reason;
}
