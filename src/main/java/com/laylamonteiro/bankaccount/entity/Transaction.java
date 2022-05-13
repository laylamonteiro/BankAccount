package com.laylamonteiro.bankaccount.entity;

import com.laylamonteiro.bankaccount.enums.TransactionDirection;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Currency;

@Data
public class Transaction {

    private String transactionId;
    private Account account;
    private BigDecimal amount;
    private Currency currency;
    private TransactionDirection direction;
    private String description;

}
