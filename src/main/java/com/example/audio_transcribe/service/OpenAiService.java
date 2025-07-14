package com.example.audio_transcribe.service;

import org.springframework.stereotype.Service;

import com.openai.client.OpenAIClient;

import com.openai.models.ChatModel;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OpenAiService {

	private final OpenAIClient openAIClient;
	public String openAiResponse(String prompt)
	{

ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
    .addUserMessage(prompt)
    .model(ChatModel.GPT_4O_MINI)
    .build();
ChatCompletion chatCompletion = openAIClient.chat().completions().create(params);
 return chatCompletion.choices().getFirst().message().content().orElse("Empty");
	}
	
}
