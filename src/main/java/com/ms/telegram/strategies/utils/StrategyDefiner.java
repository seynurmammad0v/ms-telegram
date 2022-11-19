package com.ms.telegram.strategies.utils;


import com.ms.telegram.models.enums.StrategyType;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StrategyDefiner {

    public static StrategyType define(Update update) {
        if (update.hasCallbackQuery()) return StrategyType.CALLBACK;
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasContact()) return StrategyType.CONTACT;
            else if (message.getReplyToMessage() != null) return StrategyType.REPLY;
            else if (isCommand(message)) return StrategyType.COMMAND;
            else if (message.hasText()) return StrategyType.INPUT;
        }
        return StrategyType.UNKNOWN;
    }

    public static boolean isCommand(Message message) {
        return message.hasText() && message.getText().startsWith("/");
    }
}
