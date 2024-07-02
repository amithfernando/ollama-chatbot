package com.amithfernando.ollama.chatbot.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChatService {

    private final ChatClient chatClient;


    public ChatService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String getChatResponse(String message){
        String chatResponse= this.chatClient.prompt()
                .user(message)
                .call()
                .content();
        return chatResponse;
    }
}
