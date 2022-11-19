package com.ms.telegram.strategies;

import com.ms.telegram.models.enums.StrategyType;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface MessageStrategy {
    SendMessage process(Update message);

    StrategyType getType();
}
