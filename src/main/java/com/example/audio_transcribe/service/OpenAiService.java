package com.example.audio_transcribe.service;

import org.springframework.stereotype.Service;

import com.openai.client.OpenAIClient;
import com.openai.models.ChatCompletion;
import com.openai.models.ChatCompletionCreateParams;
import com.openai.models.ChatModel;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OpenAiService {

	private final OpenAIClient openAIClient;
	public String openAiResponse(String prompt)
	{

ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
    .addUserMessage("Say this is a test")
    .model(ChatModel.GPT_4O_MINI)
    .build();
ChatCompletion chatCompletion = openAIClient.chat().completions().create(params);
 return chatCompletion.choices().getFirst().message().content().orElse("Empty");
	}
	
}
