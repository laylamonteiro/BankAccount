package com.laylamonteiro.bankaccount.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountForm {

    private Long customerId;
    private String country;
    private List<String> currencies;

}
