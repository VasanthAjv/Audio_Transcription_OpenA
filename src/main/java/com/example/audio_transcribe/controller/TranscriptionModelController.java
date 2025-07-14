package com.example.audio_transcribe.controller;


import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.audio.AudioModel;
import com.openai.models.audio.transcriptions.Transcription;
import com.openai.models.audio.transcriptions.TranscriptionCreateParams;

@RestController
@RequestMapping("/api")
public class TranscriptionModelController {


     private final OpenAIClient client 	=OpenAIOkHttpClient.fromEnv();
     
    @PostMapping("/transcribe")
    public ResponseEntity<String> transcribe(@RequestParam("file") MultipartFile file) {
       
    	try {
    		Path tempFile=Files.createTempFile("audio",file.getOriginalFilename());
    		file.transferTo(tempFile.toFile());
    		
    		TranscriptionCreateParams createParams=TranscriptionCreateParams.builder().file(tempFile).model(AudioModel.WHISPER_1).build();
    		
    		Transcription transcription = client
                    .audio()
                    .transcriptions()
                    .create(createParams)
                    .asTranscription();
    		  Files.deleteIfExists(tempFile);

              return ResponseEntity.ok(transcription.text());
    	}
    	catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
