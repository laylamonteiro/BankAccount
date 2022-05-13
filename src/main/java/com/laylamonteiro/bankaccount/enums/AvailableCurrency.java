package com.laylamonteiro.bankaccount.enums;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum AvailableCurrency {

    EUR,
    SEK,
    GBP,
    USD;

    private String value;

    public static Boolean findByValue(String value) {
        for (AvailableCurrency currency : values()) {
            if (currency.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

    private String getValue() {
        return this.value;
    }
}
