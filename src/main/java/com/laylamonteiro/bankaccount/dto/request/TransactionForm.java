package com.laylamonteiro.bankaccount.dto.request;

import com.laylamonteiro.bankaccount.enums.TransactionDirection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Currency;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionForm {

    private Long accountId;
    private BigDecimal amount;
    private Currency currency;
    private TransactionDirection direction;
    private String description;

}
