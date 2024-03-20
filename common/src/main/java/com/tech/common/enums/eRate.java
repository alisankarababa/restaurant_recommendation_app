package com.tech.common.enums;

public enum eRate {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private final int rate;

    eRate(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }
}