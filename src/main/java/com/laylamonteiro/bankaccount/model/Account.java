package com.laylamonteiro.bankaccount.model;

import lombok.Data;

import java.util.Currency;
import java.util.List;

@Data
public class Account {

    private Long customerId;
    private String accountId;
    private String country;
    private List<Currency> currencies;
    private List<Balance> balances;
    private List<Transaction> transactions;

}
