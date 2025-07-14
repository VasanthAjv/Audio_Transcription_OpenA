package com.example.audio_transcribe.model;

import com.example.audio_transcribe.enums.ProficiencyLevel;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LanguageProficiency {
    @NotBlank(message = "Language name is required")
    @Size(max = 50, message = "Language name must not exceed 50 characters")
    private String language;

    @NotNull(message = "Proficiency level is required")
    @Enumerated(EnumType.STRING)
    private ProficiencyLevel proficiencyLevel;
}