package com.laylamonteiro.bankaccount.service;

import com.laylamonteiro.bankaccount.dao.BalanceDAO;
import com.laylamonteiro.bankaccount.entity.Balance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.laylamonteiro.bankaccount.enums.AvailableCurrency.findByCurrencyValue;

@Slf4j
@Service
public class BalanceService {

    @Autowired
    private BalanceDAO balanceDAO;

    public List<Balance> findAllBalances() {
        return balanceDAO.findAll();
    }

    public List<Balance> findBalancesByAccountId(Long id) {
        return balanceDAO.findBalancesByAccountId(id);
    }

    public List<Balance> createBalances(Long accountId, List<String> incomingCurrencies) {
        List<Balance> balances = new ArrayList<>();
        incomingCurrencies.forEach(currency -> {

            Balance balance = new Balance();
            balance.setAvailableAmount(BigDecimal.valueOf(0));
            balance.setCurrency(currency);
            balance.setAccountId(accountId);
            balanceDAO.createBalance(balance);
            balances.add(balance);
        });

        return balances;
    }

    public void updateBalance(Balance balance) {
        balanceDAO.updateBalance(balance);
    }

    public void validateIfIncomingCurrenciesAreAllowed(List<String> incomingCurrencies) {
        incomingCurrencies.forEach(currency -> {
            Boolean currencyAllowed = findByCurrencyValue(currency);

            if (!currencyAllowed) {
                throw new IllegalArgumentException("Invalid currency.");
            }
        });
    }
}
