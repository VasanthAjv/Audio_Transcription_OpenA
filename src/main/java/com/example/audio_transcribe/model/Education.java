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
@Table(name = "education")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Degree is required")
    @Size(max = 100, message = "Degree must not exceed 100 characters")
    private String degree;

    @NotBlank(message = "Major is required")
    @Size(max = 100, message = "Major must not exceed 100 characters")
    private String major;

    @NotBlank(message = "Institution is required")
    @Size(max = 100, message = "Institution must not exceed 100 characters")
    private String institution;

    @NotBlank(message = "Location is required")
    @Size(max = 200, message = "Location must not exceed 200 characters")
    private String location;

    @NotNull(message = "Start date is required")
    @PastOrPresent(message = "Start date must be in the past or present")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @PastOrPresent(message = "End date must be in the past or present")
    private LocalDate endDate;

    @Min(value = 0, message = "CGPA/percentage cannot be negative")
    @Max(value = 100, message = "CGPA/percentage cannot exceed 100")
    private Double cgpaOrPercentage;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
}
