package com.tech.restaurant_service.enums;

import com.tech.common.interfaces.IExceptionMessage;

public enum eRestaurantExceptionMessage implements IExceptionMessage {
    NOT_FOUND("Restaurant not found.");

    private final String message;

    eRestaurantExceptionMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
