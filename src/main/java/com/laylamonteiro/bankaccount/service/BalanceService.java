package com.laylamonteiro.bankaccount.service;

import com.laylamonteiro.bankaccount.dao.BalanceDAO;
import com.laylamonteiro.bankaccount.entity.Balance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.laylamonteiro.bankaccount.enums.AvailableCurrency.findByValue;

@Slf4j
@Service
public class BalanceService {

    @Autowired
    private BalanceDAO balanceDAO;

    public List<Balance> findBalancesByAccountId(Long id) {
        return balanceDAO.findBalancesByAccountId(id);
    }

    public void createBalances(Long accountId, List<String> incomingCurrencies) {
        incomingCurrencies.forEach(currency -> {
            Boolean currencyAllowed = findByValue(currency);

            if (currencyAllowed) {
                Balance balance = new Balance();
                balance.setAccountId(accountId);
                balance.setAvailableAmount(BigDecimal.valueOf(0));
                balance.setCurrency(currency);
                balanceDAO.createBalance(balance);
            } else {
                throw new IllegalArgumentException("Invalid currency.");
            }
        });
    }

    public void updateBalance(Balance balance) {
        balanceDAO.updateBalance(balance);
    }
}
