package com.laylamonteiro.bankaccount.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Currency;

@Data
public class Balance {

    private BigDecimal availableAmount;
    private Currency currency;

}
