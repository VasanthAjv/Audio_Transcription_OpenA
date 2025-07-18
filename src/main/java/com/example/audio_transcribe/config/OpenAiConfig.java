package com.example.audio_transcribe.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;

@Configuration
public class OpenAiConfig {

	@Value("${spring.ai.openai.api-key}")
	private String apiKey;
	
	@Bean
	public OpenAIClient openAIClient()
	{
		return OpenAIOkHttpClient.builder().apiKey(apiKey).build();
	}
}

