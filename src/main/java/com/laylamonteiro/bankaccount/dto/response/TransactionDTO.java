package com.laylamonteiro.bankaccount.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private Long accountId;
    private Long transactionId;
    private BigDecimal amount;
    private String currency;
    private String direction;
    private String description;

}
