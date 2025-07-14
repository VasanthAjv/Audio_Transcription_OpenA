package com.example.audio_transcribe.model;


import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reference {
    @NotBlank(message = "Reference name is required")
    @Size(max = 100, message = "Reference name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Relationship is required")
    @Size(max = 100, message = "Relationship must not exceed 100 characters")
    private String relationship;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 255, message = "Email must not exceed 255 characters")
    private String email;

    @NotBlank(message = "Phone is required")
    @Size(max = 20, message = "Phone must not exceed 20 characters")
    private String phone;

    @Size(max = 100, message = "Company must not exceed 100 characters")
    private String company;
}
