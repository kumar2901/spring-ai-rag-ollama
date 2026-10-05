package com.kumar.springai.rag.controller;

import com.kumar.springai.rag.controller.service.ChatService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatService chatService;
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/v1/chat")
    private ResponseEntity<String> chat(@RequestBody String message) {

        return ResponseEntity.ok(chatService.chat(message));
    }

}
