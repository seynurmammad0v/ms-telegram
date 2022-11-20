package com.ms.telegram.configs;

import com.ms.telegram.TelegramBot;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Configuration
@AllArgsConstructor
public class BotConfig {

    private final List<BotCommand> commands;
    private final Environment env;

    @Bean
    public TelegramBot telegramBot() throws TelegramApiException {
        TelegramBot bot = TelegramBot.builder()
                .path(env.getProperty("telegram.botPath"))
                .username(env.getProperty("telegram.username"))
                .token(env.getProperty("telegram.botToken"))
                .build();
        bot.setWebhook(new SetWebhook(env.getProperty("telegram.webHookPath", "")));
        bot.execute(SetMyCommands.builder().commands(commands).build());
        return bot;
    }

}
