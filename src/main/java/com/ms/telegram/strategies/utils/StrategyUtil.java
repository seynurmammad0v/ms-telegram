package com.ms.telegram.strategies.utils;


import com.ms.telegram.models.enums.StrategyType;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StrategyUtil {

    public static StrategyType define(Update update) {
        if (update.hasCallbackQuery()) return StrategyType.CALLBACK;
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (MessageUtil.isCommand(message)) return StrategyType.COMMAND;
            else if (message.hasContact()) return StrategyType.CONTACT;
            else if (message.getReplyToMessage() != null) return StrategyType.REPLY;
            else if (message.hasText()) return StrategyType.INPUT;
        }
        return StrategyType.NOT_FOUND;
    }

}
