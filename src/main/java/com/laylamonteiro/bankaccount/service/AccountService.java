package com.laylamonteiro.bankaccount.service;

import com.laylamonteiro.bankaccount.REMOVER.dao.AccountDAO;
import com.laylamonteiro.bankaccount.dto.request.AccountForm;
import com.laylamonteiro.bankaccount.entity.Account;
import com.laylamonteiro.bankaccount.enums.AvailableCurrencies;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Currency;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import static com.laylamonteiro.bankaccount.enums.AvailableCurrencies.findByValue;

@Slf4j
@Service
public class AccountService {

    @Autowired
    private AccountDAO dao;

    public List<Account> findAll() {
        return dao.findAll();
    }

    public Account findByAccountId(String id) {
        Account existingAccount = dao.findByAccountId(id);
        if (Objects.isNull(existingAccount)) {
            String errorMessage = String.format("Account %s not found.", id);
            log.error(errorMessage);
            throw new NoSuchElementException(errorMessage);
        } else {
            return existingAccount;
        }
    }

    public Account findByCustomerId(String id) {
        Account existingAccount = dao.findByCustomerId(id);
        if (Objects.isNull(existingAccount)) {
            return new Account();
        } else {
            String errorMessage = String.format("Account %s already registered.", existingAccount.getAccountId());
            log.error(errorMessage);
            throw new DuplicateKeyException(errorMessage);
        }
    }

    public void save(Account account) {
        dao.createAccount(account);
    }

    public void create(AccountForm form) {
        validateCurrencies(form.getCurrencies());
//        Account account = findByCustomerId(form.getCustomerId());
        Account account = new Account();
        account.setCustomerId(form.getCustomerId());
        account.setAccountId(1L);
        account.setCountry(form.getCountry());
        account.setCurrencies(form.getCurrencies());
        save(account);

        log.info("Created account '{}'", account.getAccountId());
    }

    private void validateCurrencies(List<Currency> incomingCurrencies) {
        incomingCurrencies.forEach(currency -> {
            Boolean currencyAllowed = findByValue(currency.toString());
            if (!currencyAllowed) {
                throw new EnumConstantNotPresentException(AvailableCurrencies.class, currency.getSymbol());
            }
        });
    }

}
