package com.tech.common.enums;

import com.tech.common.interfaces.IExceptionMessage;

public enum eTechExceptionMessage implements IExceptionMessage {

    SERVER_ERROR("Server error."),
    RESOURCE_NOT_FOUND("Resource not found.");

    private final String message;

    eTechExceptionMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
