package com.example.audio_transcribe.model;


import java.time.LocalDate;

import com.example.audio_transcribe.validator.ValidUrl;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "certifications")
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Certification name is required")
    @Size(max = 100, message = "Certification name must not exceed 100 characters")
    private String certificationName;

    @NotBlank(message = "Issuing organization is required")
    @Size(max = 100, message = "Issuing organization must not exceed 100 characters")
    private String issuingOrganization;

    @NotNull(message = "Issue date is required")
    @PastOrPresent(message = "Issue date must be in the past or present")
    private LocalDate issueDate;

    @PastOrPresent(message = "Expiry date must be in the past or present")
    private LocalDate expiryDate;

    @Size(max = 100, message = "Credential ID must not exceed 100 characters")
    private String credentialId;

    @ValidUrl(message = "Invalid credential URL")
    private String credentialUrl;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
}
