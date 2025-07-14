package com.example.audio_transcribe.enums;
public enum TemplateType {

    MODERN("Modern & Stylish"),
    MINIMAL("Minimal & Clean"),
    CORPORATE("Professional / Corporate"),
    CREATIVE("Creative / Colorful"),
    TRADITIONAL("Traditional Format"),
    ATS_FRIENDLY("ATS-Friendly Template");

    private final String label;

    TemplateType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

