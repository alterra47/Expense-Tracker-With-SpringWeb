package com.tutorial.expensetracker;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @PostMapping
    public Map<String, String> chat(
            @RequestBody Map<String, String> request) {

        String message = request.get("message");

        String response = chatClient
                .prompt()
                .system("""
                        You are an AI assistant for an expense tracking application.

                        Help users with:
                        - personal budgeting
                        - saving money
                        - understanding expenses
                        - basic financial planning

                        Keep answers concise and practical.

                        You currently do not have access to the user's
                        stored expense data. Do not pretend that you do.
                        """)
                .user(message)
                .call()
                .content();

        return Map.of("response", response);
    }
}