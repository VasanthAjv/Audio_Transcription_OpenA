package com.example.audio_transcribe.model;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "work_experience")
public class WorkExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Job title is required")
    @Size(max = 100, message = "Job title must not exceed 100 characters")
    private String jobTitle;

    @NotBlank(message = "Company name is required")
    @Size(max = 100, message = "Company name must not exceed 100 characters")
    private String companyName;

    @NotBlank(message = "Location is required")
    @Size(max = 200, message = "Location must not exceed 200 characters")
    private String location;

    @NotNull(message = "Start date is required")
    @PastOrPresent(message = "Start date must be in the past or present")
    private LocalDate startDate;

    @PastOrPresent(message = "End date must be in the past or present")
    private LocalDate endDate;

    @NotBlank(message = "Responsibilities are required")
    @Size(max = 2000, message = "Responsibilities must not exceed 2000 characters")
    @Column(length = 2000)
    private String responsibilities;

    @NotBlank(message = "Employment type is required")
    @Size(max = 50, message = "Employment type must not exceed 50 characters")
    private String employmentType;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
}