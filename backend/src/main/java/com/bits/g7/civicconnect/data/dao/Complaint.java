package com.bits.g7.civicconnect.data.dao;

import jakarta.persistence.*;
import lombok.*;
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
    private IdType idType;

    @Column(nullable = false)
    private String idNumber; // Used for the "Track My Complaint" feature

    @Enumerated(EnumType.STRING)
    private ComplaintType type;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private ComplaintStatus status = ComplaintStatus.PENDING;

    @CreationTimestamp
    private LocalDateTime submittedAt;

    public enum IdType { NATIONAL_ID, PASSPORT, VOTER_ID, PAN }
    public enum ComplaintType { SANITATION, ROAD, ELECTRICITY, WATER, OTHER }
    public enum ComplaintStatus { PENDING, IN_PROGRESS, RESOLVED, REJECTED }
}