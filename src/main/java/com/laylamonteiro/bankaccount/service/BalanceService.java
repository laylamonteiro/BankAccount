package com.laylamonteiro.bankaccount.service;

import com.laylamonteiro.bankaccount.dao.BalanceDAO;
import com.laylamonteiro.bankaccount.entity.Balance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.List;

import static com.laylamonteiro.bankaccount.enums.AvailableCurrency.findByValue;

@Slf4j
@Service
public class BalanceService {

    @Autowired
    private BalanceDAO balanceDAO;

    public List<Balance> findAll() {
        return balanceDAO.findAll();
    }

    public List<Balance> findBalancesByAccountId(Long id) {
        List<Balance> existingBalances = balanceDAO.findBalancesByAccountId(id);

        if (existingBalances.isEmpty()) {
            return Collections.emptyList();
        }

        return existingBalances;
    }


    public List<Balance> createBalances(Long accountId, List<Currency> incomingCurrencies) {
        List<Balance> balances = new ArrayList<>();

        incomingCurrencies.forEach(currency -> {
            String currencySymbol = currency.toString();
            Boolean currencyAllowed = findByValue(currencySymbol);

            if (currencyAllowed) {
                Balance balance = new Balance();
                balance.setAccountId(accountId);
                balance.setCurrency(currency.toString());
                balances.add(balance);
                balanceDAO.createBalance(balance);
            } else {
                throw new IllegalArgumentException("Invalid currency.");
            }
        });

        return balances;
    }
}
