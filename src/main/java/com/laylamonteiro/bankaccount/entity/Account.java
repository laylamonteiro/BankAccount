package com.laylamonteiro.bankaccount.entity;

import lombok.Data;

import java.util.Currency;
import java.util.List;

@Data
public class Account {

    private Long accountId;
    private Long customerId;
    private String country;
    private List<Currency> currencies;
    private List<Balance> balances;
    private List<Transaction> transactions;

}
