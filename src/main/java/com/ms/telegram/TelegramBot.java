package com.ms.telegram;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;

@Builder
@Slf4j
public class TelegramBot extends TelegramWebhookBot {
    private String username;
    private String token;
    private String path;

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return null;
    }

    @Override
    public String getBotPath() {
        return path;
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    public <T extends Serializable, Method extends BotApiMethod<T>> T executeMessage(Method method) {
        try {
            return this.execute(method);
        } catch (TelegramApiException e) {
            log.error("Cannot execute message: {}", e.getCause().getMessage());
            throw new RuntimeException(e);
        }
    }

    public Message executePhoto(SendPhoto sendPhoto) {
        try {
            return this.execute(sendPhoto);
        } catch (TelegramApiException e) {
            log.error("Cannot send photo: {}", e.getCause().getMessage());
            throw new RuntimeException(e);
        }
    }
}
