package com.laylamonteiro.bankaccount.enums;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum AvailableCurrency {

    EUR,
    SEK,
    GBP,
    USD;

    public static Boolean findByValue(String value) {
        for (AvailableCurrency currency : values()) {
            if (currency.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
