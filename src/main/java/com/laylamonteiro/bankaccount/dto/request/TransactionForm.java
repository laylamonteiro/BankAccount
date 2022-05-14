package com.laylamonteiro.bankaccount.dto.request;

import com.laylamonteiro.bankaccount.enums.TransactionDirection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Currency;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionForm {

    @NotNull(message = "AccountId is mandatory")
    private Long accountId;

    private BigDecimal amount;
    private String currency;
    private TransactionDirection direction;

    @NotNull(message = "Description is mandatory")
    private String description;

}
