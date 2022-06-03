package com.assignment.frieghtous.utils.enums;

public enum CoinValueEnum {
    TEN_CENTS(0.10), TWENTY_CENTS(0.20), FIFTY_CENTS(0.50), ONE_DOLLAR(1);

    public final double value;

    private CoinValueEnum(double value) {
        this.value = value;
    }
}
