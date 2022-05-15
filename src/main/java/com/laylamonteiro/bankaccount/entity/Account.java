package com.laylamonteiro.bankaccount.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private Long accountId;
    private Long customerId;
    private String country;

}
