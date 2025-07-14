package com.example.audio_transcribe.validator;

import com.example.audio_transcribe.enums.ExperienceLevel;
import com.example.audio_transcribe.model.Candidate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CandidateConsistencyValidator implements ConstraintValidator<CandidateConsistency, Candidate> {
    @Override
    public boolean isValid(Candidate candidate, ConstraintValidatorContext context) {
        boolean isValid = true;

        // Rule 1: Years of experience must be 0 for FRESHER
        if (candidate.getExperienceLevel() == ExperienceLevel.FRESHER && 
            (candidate.getYearsOfExperience() != null && candidate.getYearsOfExperience() > 0)) {
            context.buildConstraintViolationWithTemplate("Years of experience must be 0 for FRESHER")
                   .addPropertyNode("yearsOfExperience")
                   .addConstraintViolation();
            isValid = false;
        }

        // Rule 2: Years of experience must be positive for non-FRESHER
        if (candidate.getExperienceLevel() != ExperienceLevel.FRESHER && 
            (candidate.getYearsOfExperience() == null || candidate.getYearsOfExperience() <= 0)) {
            context.buildConstraintViolationWithTemplate("Years of experience must be positive for non-FRESHER")
                   .addPropertyNode("yearsOfExperience")
                   .addConstraintViolation();
            isValid = false;
        }

        // Rule 3: If includePhoto is true, profilePictureUrl must be provided
        if (Boolean.TRUE.equals(candidate.getIncludePhoto()) && 
            (candidate.getProfilePictureUrl() == null || candidate.getProfilePictureUrl().isBlank())) {
            context.buildConstraintViolationWithTemplate("Profile picture URL is required when includePhoto is true")
                   .addPropertyNode("profilePictureUrl")
                   .addConstraintViolation();
            isValid = false;
        }

        return isValid;
    }
}