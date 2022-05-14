package com.laylamonteiro.bankaccount.dto.response;

import com.laylamonteiro.bankaccount.entity.Balance;
import com.laylamonteiro.bankaccount.enums.TransactionDirection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    private Long accountId;
    private Long transactionId;
    private BigDecimal amount;
    private String currency;
    private TransactionDirection direction;
    private String description;
    private List<Balance> balance;

}
