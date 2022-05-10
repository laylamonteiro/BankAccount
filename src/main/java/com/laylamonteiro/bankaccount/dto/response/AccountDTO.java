package com.laylamonteiro.bankaccount.dto.response;

import com.laylamonteiro.bankaccount.model.Account;
import com.laylamonteiro.bankaccount.model.Balance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private Long accountId;
    private Long customerId;
    private List<Balance> balances;

    public AccountDTO(Account account) {

    }

}
