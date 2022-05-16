package com.laylamonteiro.bankaccount.enums;

public enum TransactionDirection {

    IN,
    OUT;

    public static Boolean findByTransactionDirectionValue(String value) {
        for (TransactionDirection direction : values()) {
            if (direction.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

}
