package com.bits.g7.civicconnect.data.dto;

import com.bits.g7.civicconnect.data.dao.Complaint;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComplaintResponse {

    private Long id;
    private String fullName;
    private Complaint.IdType idType;
    private String idNumber;
    private Complaint.ComplaintType complaintType;
    private String complaint;
    private Complaint.ComplaintStatus status;
    private LocalDateTime submittedAt;

    /**
     * Maps the database Entity to a Response DTO
     */
    public static ComplaintResponse fromEntity(Complaint entity) {
        return ComplaintResponse.builder()
                .id(entity.getId())
                .fullName(entity.getFullName())
                .idType(entity.getIdType())
                .idNumber(entity.getIdNumber())
                .complaintType(entity.getType())
                .complaint(entity.getDescription())
                .status(entity.getStatus())
                .submittedAt(entity.getSubmittedAt())
                .build();
    }
}