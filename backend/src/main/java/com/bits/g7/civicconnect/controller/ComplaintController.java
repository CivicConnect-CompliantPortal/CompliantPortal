package com.bits.g7.civicconnect.controller;


import com.bits.g7.civicconnect.data.dto.ComplaintRequest;
import com.bits.g7.civicconnect.data.dto.ComplaintResponse;
import com.bits.g7.civicconnect.service.ComplaintService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/complaints")
@RequiredArgsConstructor
public class ComplaintController {

    private final ComplaintService complaintService;

    @PostMapping
    public ResponseEntity<ComplaintResponse> create(@Valid @RequestBody ComplaintRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(complaintService.submitComplaint(request));
    }

    @GetMapping
    public ResponseEntity<Page<ComplaintResponse>> list(Pageable pageable) {
        return ResponseEntity.ok(complaintService.getAllComplaints(pageable));
    }
}