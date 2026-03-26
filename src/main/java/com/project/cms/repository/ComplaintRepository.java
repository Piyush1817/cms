package com.project.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.cms.entity.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
}