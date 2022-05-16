package com.laylamonteiro.bankaccount.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionForm {

    @NotNull(message = "AccountId is mandatory")
    private Long accountId;

    private BigDecimal amount;
    private String currency;
    private String direction;

    @NotNull(message = "Description is mandatory")
    private String description;

}
