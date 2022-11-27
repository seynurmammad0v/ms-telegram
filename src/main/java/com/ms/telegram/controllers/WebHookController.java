package com.ms.telegram.controllers;

import com.ms.telegram.strategies.MessageContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@RequiredArgsConstructor
@Slf4j
//todo create custom logger for template logs
public class WebHookController {
    private final MessageContext messageContext;

    @PostMapping("callback/webhook")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        try {
            return messageContext.process(update);
        } catch (Exception ex) {
            log.error("ActionLog.WebHookController.onUpdateReceived.error " +
                            "while processing the message userID {} exp {}",
                    update.getMessage().getFrom().getId(), ex.getMessage());
            return null;
        }
    }

    @GetMapping
    public ResponseEntity<String> health() {
        return ResponseEntity.ok().build();
    }
}
