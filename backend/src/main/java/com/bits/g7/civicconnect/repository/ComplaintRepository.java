package com.bits.g7.civicconnect.repository;

import com.bits.g7.civicconnect.data.dao.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    // Find all complaints submitted by a specific ID Number
    List<Complaint> findByIdNumber(String idNumber);

    // Find complaints by status (e.g., all "PENDING" items for admin dashboard)
    List<Complaint> findByStatus(Complaint.ComplaintStatus status);
}