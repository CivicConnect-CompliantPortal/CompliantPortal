package com.bits.g7.civicconnect.data.dto;

import com.bits.g7.civicconnect.data.dao.Complaint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ComplaintRequest {

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotNull(message = "ID Type is required")
    private Complaint.IdType idType;

    @NotBlank(message = "ID Number is required")
    private String idNumber;

    @NotNull(message = "Complaint Type is required")
    private Complaint.ComplaintType complaintType;

    @NotBlank(message = "Complaint description is required")
    private String complaint; // Maps to the text area in UI
}