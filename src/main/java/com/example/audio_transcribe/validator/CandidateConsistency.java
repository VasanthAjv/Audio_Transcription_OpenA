package com.example.audio_transcribe.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = CandidateConsistencyValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CandidateConsistency{
	String messege()default "Candidate data is inconsistent";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
