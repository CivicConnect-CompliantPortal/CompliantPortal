package com.bits.g7.civicconnect.data.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "complaints")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IdType idType;

    @Column(nullable = false)
    private String idNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ComplaintType type;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ComplaintStatus status = ComplaintStatus.PENDING;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime submittedAt;

    public enum IdType {NATIONAL_ID, PASSPORT, DRIVERS_LICENSE}

    public enum ComplaintType {SANITATION, ROAD_REPAIR, WATER_SUPPLY, ELECTRICITY, OTHER}

    public enum ComplaintStatus {PENDING, IN_PROGRESS, RESOLVED, REJECTED}
}