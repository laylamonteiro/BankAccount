package com.laylamonteiro.bankaccount.entity;

import lombok.Data;

@Data
public class Account {

    private Long accountId;
    private Long customerId;
    private String country;
    private Long balances;
    private Long transactions;

}
