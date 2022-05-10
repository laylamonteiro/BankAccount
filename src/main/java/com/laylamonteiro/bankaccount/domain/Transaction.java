package com.laylamonteiro.bankaccount.domain;

import com.laylamonteiro.bankaccount.enums.TransactionDirection;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Currency;

@Data
public class Transaction {

    private Long accountId;
    private Long transactionId;
    private BigDecimal amount;
    private Currency currency;
    private TransactionDirection direction;
    private String description;
    private Balance balance;

}
