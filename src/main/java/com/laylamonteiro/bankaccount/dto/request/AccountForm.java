package com.laylamonteiro.bankaccount.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Currency;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountForm {

    private String customerId;
    private String country;
    private List<Currency> currencies;

}
