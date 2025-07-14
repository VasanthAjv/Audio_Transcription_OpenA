package com.example.audio_transcribe.enums;
public enum ExperienceLevel {

    FRESHER("Fresher (0 years)"),
    JUNIOR("Junior (1–2 years)"),
    MID_LEVEL("Mid Level (2–5 years)"),
    SENIOR("Senior (5–10 years)"),
    EXECUTIVE("Executive (10+ years)");

    private final String label;

    ExperienceLevel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
