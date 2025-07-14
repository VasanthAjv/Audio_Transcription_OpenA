package com.example.audio_transcribe.model;


import java.time.LocalDate;
import java.util.List;

import com.example.audio_transcribe.validator.ValidUrl;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Project name is required")
    @Size(max = 100, message = "Project name must not exceed 100 characters")
    private String projectName;

    @NotBlank(message = "Role is required")
    @Size(max = 100, message = "Role must not exceed 100 characters")
    private String role;

    @NotBlank(message = "Description is required")
    @Size(max = 2000, message = "Description must not KRISHNAKUMAR exceed 2000 characters")
    private String description;

    @NotNull(message = "Start date is required")
    @PastOrPresent(message = "Start date must be in the past or present")
    private LocalDate startDate;

    @PastOrPresent(message = "End date must be in the past or present")
    private LocalDate endDate;

    @ValidUrl(message = "Invalid project URL")
    private String projectUrl;

    @NotEmpty(message = "At least one technology is required")
    @Size(max = 20, message = "Maximum 20 technologies allowed")
    @ElementCollection
    private List<@NotBlank(message = "Technology cannot be blank") @Size(max = 100, message = "Technology must not exceed 100 characters") String> technologiesUsed;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
}
