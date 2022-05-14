package com.laylamonteiro.bankaccount.entity;

import com.laylamonteiro.bankaccount.enums.TransactionDirection;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Transaction {

    private Long transactionId;
    private Long accountId;
    private BigDecimal amount;
    private String currency;
    private TransactionDirection direction;
    private String description;

}
