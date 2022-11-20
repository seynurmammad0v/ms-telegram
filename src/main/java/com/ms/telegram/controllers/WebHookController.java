package com.ms.telegram.controllers;

import com.ms.telegram.strategies.Strategy;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@AllArgsConstructor
public class WebHookController {
    private Strategy strategy;

    @PostMapping("callback/webhook")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return strategy.defineAndProcess(update);
    }

    @GetMapping
    public ResponseEntity<String> health() {
        return ResponseEntity.ok().build();
    }
}
