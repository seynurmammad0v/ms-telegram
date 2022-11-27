package com.ms.telegram.services;

import com.ms.telegram.daos.repos.SessionRepository;
import com.ms.telegram.models.enums.Command;
import com.ms.telegram.strategies.utils.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository repo;

    public Optional<SendMessage> validateSession(Update update) {
        Optional<SendMessage> empty = Optional.empty();

        if (MessageUtil.defineCommand(update.getMessage()) == Command.START)
            return empty;

        //todo refactor,extract message into another class
        return repo.getByUserID(update.getMessage().getFrom().getId())
                .map(s -> empty)
                .orElse(Optional.of(SendMessage.builder()
                        .chatId(update.getMessage().getChatId().toString())
                        .text("create session before")
                        .build()));

    }


}
