package com.example.audio_transcribe.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidPhoneValidator implements ConstraintValidator<ValidPhone,String>{

	 @Override
	    public boolean isValid(String value, ConstraintValidatorContext context) {
	        if (value == null || value.isBlank()) {
	            return false;
	        }
	        // Basic phone number validation (e.g., +1234567890, 123-456-7890)
	        return value.matches("^(\\+\\d{1,3}[- ]?)?\\d{10}$|^\\d{3}[- ]?\\d{3}[- ]?\\d{4}$");
	    }
}
