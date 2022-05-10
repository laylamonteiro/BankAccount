package com.laylamonteiro.bankaccount.domain;

import lombok.Data;

import java.util.Currency;
import java.util.List;

@Data
public class Account {

    private Long customerId;
    private Account account;
    private String country;
    private List<Currency> currencies;
    private List<Balance> balances;

}
