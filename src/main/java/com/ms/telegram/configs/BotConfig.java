package com.ms.telegram.configs;

import com.ms.telegram.TelegramBot;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import java.util.List;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "telegram.bot")
@RequiredArgsConstructor
public class BotConfig {
    private String webHookPath;
    private String botUserName;
    private String botToken;
    private String botPath;

    public final List<BotCommand> commands;

    @Bean
    public TelegramBot telegramBot() throws TelegramApiException {
        TelegramBot bot = TelegramBot.builder()
                .botPath(botPath)
                .botUsername(botUserName)
                .botToken(botToken)
                .build();

        bot.setWebhook(new SetWebhook(webHookPath));
        return bot;
    }

    @PostConstruct
    public void setCommands() throws TelegramApiException {
        telegramBot().execute(SetMyCommands.builder().commands(commands).build());
    }

}
