package com.laylamonteiro.bankaccount.model;

import com.laylamonteiro.bankaccount.enums.TransactionDirection;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Currency;

@Data
public class Transaction {

    private Account account;
    private String transactionId;
    private BigDecimal amount;
    private Currency currency;
    private TransactionDirection direction;
    private String description;

}
