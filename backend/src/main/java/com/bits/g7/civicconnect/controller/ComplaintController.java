package com.bits.g7.civicconnect.controller;


import com.bits.g7.civicconnect.data.dao.Complaint;
import com.bits.g7.civicconnect.data.dto.ComplaintRequest;
import com.bits.g7.civicconnect.data.dto.ComplaintResponse;
import com.bits.g7.civicconnect.service.ComplaintService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/complaints")
@RequiredArgsConstructor
public class ComplaintController {

    private final ComplaintService service;

    // POST: User submits from the Complaint Page
    @PostMapping
    public ResponseEntity<ComplaintResponse> submit(@Valid @RequestBody ComplaintRequest request) {
        return ResponseEntity.ok(service.createComplaint(request));
    }

    // GET: User tracks status via ID number
    @GetMapping("/track/{idNumber}")
    public ResponseEntity<List<ComplaintResponse>> track(@PathVariable String idNumber) {
        return ResponseEntity.ok(service.trackByIdNumber(idNumber));
    }

    // PATCH: Officer updates status from Dashboard
    @PatchMapping("/{id}/status")
    public ResponseEntity<ComplaintResponse> updateStatus(
            @PathVariable Long id,
            @RequestParam Complaint.ComplaintStatus status) {
        return ResponseEntity.ok(service.updateStatus(id, status));
    }
}