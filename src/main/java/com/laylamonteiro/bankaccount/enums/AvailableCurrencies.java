package com.laylamonteiro.bankaccount.enums;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum AvailableCurrencies {

    EUR,
    SEK,
    GBP,
    USD;

    private String value;

    public static Boolean findByValue(String value) {
        for (AvailableCurrencies currency : values()) {
            if (currency.getValue().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

    private String getValue() {
        return this.value;
    }
}
