package com.example.audio_transcribe.enums;

public enum ProficiencyLevel {
    
    BEGINNER("Beginner"),
    ELEMENTARY("Elementary"),
    INTERMEDIATE("Intermediate"),
    UPPER_INTERMEDIATE("Upper Intermediate"),
    ADVANCED("Advanced"),
    PROFICIENT("Proficient"),
    NATIVE("Native / Bilingual");

    private final String label;

    ProficiencyLevel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

