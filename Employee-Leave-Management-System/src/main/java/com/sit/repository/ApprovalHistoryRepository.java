package com.sit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sit.entity.ApprovalHistory;

public interface ApprovalHistoryRepository extends JpaRepository<ApprovalHistory, Long> {

}
