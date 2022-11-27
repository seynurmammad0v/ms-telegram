package com.ms.telegram.models.enums;

import java.util.Objects;

public enum Command {
    START("/start"),
    STOP("/stop"),
    NOT_FOUND("NOT_FOUND");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command findCommand(String value) {
        for (Command command : Command.values()) {
            if (Objects.equals(command.value, value)) {
                return command;
            }
        }
        return NOT_FOUND;
    }

}
