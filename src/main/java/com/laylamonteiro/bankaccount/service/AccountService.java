package com.laylamonteiro.bankaccount.service;

import com.laylamonteiro.bankaccount.REMOVER.dao.AccountDAO;
import com.laylamonteiro.bankaccount.REMOVER.dao.BalanceDAO;
import com.laylamonteiro.bankaccount.dto.request.AccountForm;
import com.laylamonteiro.bankaccount.dto.response.AccountDTO;
import com.laylamonteiro.bankaccount.entity.Account;
import com.laylamonteiro.bankaccount.entity.Balance;
import com.laylamonteiro.bankaccount.enums.AvailableCurrency;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.laylamonteiro.bankaccount.enums.AvailableCurrency.findByValue;

@Slf4j
@Service
public class AccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private BalanceDAO balanceDAO;

    public List<AccountDTO> findAll() {
        List<Account> accounts = accountDAO.findAll();
        List<AccountDTO> dtos = new ArrayList<>();

        accounts.forEach(account -> {
            AccountDTO dto = toDTO(account);
            dtos.add(dto);
        });
        return dtos;
    }

    public AccountDTO findByAccountId(String id) {
        Account existingAccount = accountDAO.findByAccountId(id);
        if (Objects.isNull(existingAccount)) {
            String errorMessage = String.format("Account %s not found.", id);
            log.error(errorMessage);
            throw new NoSuchElementException(errorMessage);
        } else {
            return toDTO(existingAccount);
        }
    }

    public Account findByCustomerId(Long id) {
        Account existingAccount = accountDAO.findByCustomerId(id);
        if (Objects.isNull(existingAccount)) {
            return new Account();
        } else {
            String errorMessage = String.format("Account %s already registered.", existingAccount.getAccountId());
            log.error(errorMessage);
            throw new DuplicateKeyException(errorMessage);
        }
    }

    public void create(AccountForm form) {
        Account account = findByCustomerId(form.getCustomerId());
        account.setCustomerId(form.getCustomerId());
        account.setAccountId(3L);
        account.setCountry(form.getCountry());
        account.setBalances(1L);
        account.setTransactions(1L);

        createBalances(account.getAccountId(), form.getCurrencies());
        accountDAO.createAccount(account);

        log.info("Created account '{}'", account.getAccountId());
    }

    private void createBalances(Long accountId, List<Currency> incomingCurrencies) {
        incomingCurrencies.forEach(currency -> {
            String currencySymbol = currency.toString();
            Boolean currencyAllowed = findByValue(currencySymbol);
            if (!currencyAllowed) {
                throw new EnumConstantNotPresentException(AvailableCurrency.class, currency.getSymbol());
            }
            Balance balance = new Balance();
            balance.setAccountId(accountId);
            balance.setCurrency(currency.toString());
            balanceDAO.createBalance(balance);
        });
    }

    private AccountDTO toDTO(Account existingAccount) {
        return new AccountDTO(
                existingAccount.getAccountId(),
                existingAccount.getCustomerId(),
                Collections.singletonList(new Balance())
        );
    }

}
