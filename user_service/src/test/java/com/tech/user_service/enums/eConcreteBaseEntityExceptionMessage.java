package com.tech.user_service.enums;

import com.tech.common.interfaces.IExceptionMessage;

public enum eConcreteBaseEntityExceptionMessage implements IExceptionMessage {

    NOT_FOUND("ContreteBaseEntity not found.");

    private final String message;

    eConcreteBaseEntityExceptionMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
