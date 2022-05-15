package com.laylamonteiro.bankaccount.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Balance {

    private Long accountId;
    private BigDecimal availableAmount;
    private String currency;

}
