package com.example.audio_transcribe.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.audio_transcribe.service.OpenAiService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/openai")
@RequiredArgsConstructor
public class OpenAiController {
	
	
	private final OpenAiService openAiService;
	
	@GetMapping("/response")
	public ResponseEntity<String> getOpenAiResponse(@RequestBody String prompt)
	{
		String response=openAiService.openAiResponse(prompt);
		return ResponseEntity.ok(response);
	}

}
