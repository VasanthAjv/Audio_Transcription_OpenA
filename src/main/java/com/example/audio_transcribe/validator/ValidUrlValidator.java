package com.example.audio_transcribe.validator;

import java.net.URL;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidUrlValidator implements ConstraintValidator<ValidUrl,String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		 if (value == null || value.isBlank()) {
	            return true; // Allow null/empty URLs as they are optional
	        }
	        try {
	            new URL(value).toURI();
	            return value.matches("^(https?://)?([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$");
	        } catch (Exception e) {
	            return false;
	        }
	}

}
