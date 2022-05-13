package com.laylamonteiro.bankaccount.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Balance {

    private Long accountId;
    private BigDecimal availableAmount = BigDecimal.ZERO;
    private String currency;

}
