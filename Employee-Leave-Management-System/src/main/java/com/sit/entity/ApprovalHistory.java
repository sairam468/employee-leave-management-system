package com.sit.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;  
import lombok.Data;

@Entity
@Data
public class ApprovalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long leaveRequestId;
    private String approvedBy;
    private String status;
    private String comments;
    private LocalDateTime timestamp;
}
