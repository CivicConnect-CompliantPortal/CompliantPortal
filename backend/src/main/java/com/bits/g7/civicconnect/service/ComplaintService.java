package com.bits.g7.civicconnect.service;

import com.bits.g7.civicconnect.data.dao.Complaint;
import com.bits.g7.civicconnect.data.dto.ComplaintRequest;
import com.bits.g7.civicconnect.data.dto.ComplaintResponse;
import com.bits.g7.civicconnect.repository.ComplaintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintService {

    private final ComplaintRepository repository;

    @Transactional
    public ComplaintResponse createComplaint(ComplaintRequest request) {
        Complaint complaint = Complaint.builder()
                .fullName(request.getFullName())
                .idType(request.getIdType())
                .idNumber(request.getIdNumber())
                .type(request.getComplaintType())
                .description(request.getComplaint())
                .status(Complaint.ComplaintStatus.PENDING)
                .build();
        return ComplaintResponse.fromEntity(repository.save(complaint));
    }

    @Transactional(readOnly = true)
    public List<ComplaintResponse> trackByIdNumber(String idNumber) {
        return repository.findByIdNumber(idNumber).stream()
                .map(ComplaintResponse::fromEntity)
                .toList();
    }

    @Transactional
    public ComplaintResponse updateStatus(Long id, Complaint.ComplaintStatus newStatus) {
        Complaint complaint = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
        complaint.setStatus(newStatus);
        return ComplaintResponse.fromEntity(repository.save(complaint));
    }
}