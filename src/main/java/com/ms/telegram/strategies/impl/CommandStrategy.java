package com.ms.telegram.strategies.impl;

import com.ms.telegram.models.enums.StrategyType;
import com.ms.telegram.strategies.MessageStrategy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class CommandStrategy implements MessageStrategy {
    public SendMessage process(Update message) {
        return SendMessage.builder()
                .chatId(message.getMessage().getChatId().toString())
                .text("Ok command")
                .build();
    }

    public StrategyType getType() {
        return StrategyType.COMMAND;
    }
}
