package com.laylamonteiro.bankaccount.entity;

import com.laylamonteiro.bankaccount.enums.TransactionDirection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    private Long transactionId;
    private Long accountId;
    private BigDecimal amount;
    private String currency;
    private TransactionDirection direction;
    private String description;

}
