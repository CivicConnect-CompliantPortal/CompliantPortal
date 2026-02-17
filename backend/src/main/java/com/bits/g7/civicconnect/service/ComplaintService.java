package com.bits.g7.civicconnect.service;

import com.bits.g7.civicconnect.data.dao.Complaint;
import com.bits.g7.civicconnect.data.dto.ComplaintRequest;
import com.bits.g7.civicconnect.data.dto.ComplaintResponse;
import com.bits.g7.civicconnect.repository.ComplaintRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ComplaintService {

    private final ComplaintRepository complaintRepository;

    public ComplaintResponse submitComplaint(ComplaintRequest request) {
        log.info("Submitting new civic complaint for: {}", request.getFullName());

        Complaint complaint = Complaint.builder().fullName(request.getFullName()).idType(request.getIdType()).idNumber(request.getIdNumber()).type(request.getComplaintType()).description(request.getComplaint()).status(Complaint.ComplaintStatus.PENDING).build();

        return ComplaintResponse.fromEntity(complaintRepository.save(complaint));
    }

    public Page<ComplaintResponse> getAllComplaints(Pageable pageable) {
        return complaintRepository.findAll(pageable).map(ComplaintResponse::fromEntity);
    }
}