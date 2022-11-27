package com.ms.telegram.strategies;


import com.ms.telegram.models.enums.StrategyType;
import com.ms.telegram.services.SessionService;
import com.ms.telegram.strategies.utils.StrategyUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageContext {

    private static final Map<StrategyType, MessageStrategy> cache = new HashMap<>();

    public final List<MessageStrategy> strategies;
    public final SessionService sessionService;

    //todo add AOP style logging
    public BotApiMethod<?> process(Update update) {
        Optional<SendMessage> error = sessionService.validateSession(update);
        if (error.isPresent()) return error.get();
        MessageStrategy strategy = cache.get(StrategyUtil.define(update));
        if (strategy == null) {
            log.warn("ActionLog.MessageContext.process.error strategy isn't found");
            return null;
        }
        return strategy.process(update);
    }


    @PostConstruct
    public void initStrategyCache() {
        for (MessageStrategy strategy : strategies) {
            cache.put(strategy.getType(), strategy);
        }
    }

}
