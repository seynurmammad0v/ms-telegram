package com.ms.telegram.strategies.impl;

import com.ms.telegram.models.enums.StrategyType;
import com.ms.telegram.strategies.MessageStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


@Service
@Slf4j
public class InputStrategy implements MessageStrategy {


    @Override
    public SendMessage process(Update message) {
        return null;
    }

    @Override
    public StrategyType getType() {
        return StrategyType.INPUT;
    }
}
