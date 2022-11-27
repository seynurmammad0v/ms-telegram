package com.ms.telegram.strategies.utils;

import com.ms.telegram.models.enums.Command;
import org.telegram.telegrambots.meta.api.objects.Message;

public class MessageUtil {
    public static boolean isCommand(Message message) {
        return message.hasText() && message.getText().startsWith("/");
    }

    public static Command defineCommand(Message message) {
        if (isCommand(message)) {
            String msg = message.getText().toLowerCase();
            return Command.findCommand(msg);
        }
        return Command.NOT_FOUND;
    }
}
