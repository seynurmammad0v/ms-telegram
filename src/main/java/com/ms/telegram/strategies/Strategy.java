package com.ms.telegram.strategies;


import com.ms.telegram.models.enums.StrategyType;
import com.ms.telegram.strategies.utils.StrategyDefiner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jvnet.hk2.annotations.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class Strategy {

    private static final Map<StrategyType, MessageStrategy> cache = new HashMap<>();

    public final List<MessageStrategy> strategies;

    @PostConstruct
    public void initStrategyCache() {
        for (MessageStrategy strategy : strategies) {
            cache.put(strategy.getType(), strategy);
        }
    }

    public BotApiMethod<?> defineAndProcess(Update update) {
        var strategy = cache.get(StrategyDefiner.define(update));
        if (strategy == null){
            log.warn("Strategy isn't found");
            return null;
        }
        return strategy.process(update);
    }

}
