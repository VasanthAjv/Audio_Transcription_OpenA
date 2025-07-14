package com.example.audio_transcribe.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.audio_transcribe.enums.ExperienceLevel;

@Service
public class OpenAIService {
    private final RestTemplate restTemplate;
    @Value("${spring.ai.openai.api-key}")
    private String openAiApiKey;

    public OpenAIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String generateObjective(String jobRole, String industry, ExperienceLevel experienceLevel) {
        String prompt = String.format(
            "Generate a professional career objective for a %s with %s experience in the %s industry. Keep it concise, under 100 words.",
            jobRole != null ? jobRole : "professional",
            experienceLevel != null ? experienceLevel.name().toLowerCase() : "unspecified",
            industry != null ? industry : "unspecified"
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openAiApiKey);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-4");
        requestBody.put("prompt", prompt);
        requestBody.put("max_tokens", 100);
        requestBody.put("temperature", 0.7);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
        try {
            String response = restTemplate.postForObject(
                "https://api.openai.com/v1/completions",
                request,
                String.class
            );
            return parseOpenAiResponse(response);
        } catch (Exception e) {
            return "Dynamic, motivated professional seeking opportunities to leverage skills and experience.";
        }
    }

    private String parseOpenAiResponse(String response) {
        // Simplified parsing; in production, use JSON parsing (e.g., Jackson)
        if (response != null && response.contains("\"text\":")) {
            int start = response.indexOf("\"text\":") + 8;
            int end = response.indexOf("\"", start);
            return response.substring(start, end).trim();
        }
        return "Dynamic, motivated professional seeking opportunities to leverage skills and experience.";
    }
}