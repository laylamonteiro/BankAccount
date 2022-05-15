package com.laylamonteiro.bankaccount.service;

import com.laylamonteiro.bankaccount.dao.AccountDAO;
import com.laylamonteiro.bankaccount.dto.request.AccountForm;
import com.laylamonteiro.bankaccount.dto.response.AccountDTO;
import com.laylamonteiro.bankaccount.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class AccountService {

    private final AccountDAO accountDAO;
    private final BalanceService balanceService;

    @Autowired
    public AccountService(AccountDAO accountDAO, BalanceService balanceService) {
        this.accountDAO = accountDAO;
        this.balanceService = balanceService;
    }

    public List<AccountDTO> findAll() {
        List<Account> accounts = accountDAO.findAll();
        return toDTO(accounts);
    }

    public AccountDTO findByAccountId(Long id) throws NotFoundException {
        Account existingAccount = accountDAO.findByAccountId(id);

        if (Objects.isNull(existingAccount)) {
            String errorMessage = String.format("Account %s not found.", id);
            log.error(errorMessage);
            throw new NotFoundException(errorMessage);
        }

        log.debug("Found account '{}' .", existingAccount.getAccountId());
        return toDTO(existingAccount);
    }

    public AccountDTO create(AccountForm form) {
        Account account = new Account();
        account.setCustomerId(form.getCustomerId());
        account.setCountry(form.getCountry());

        accountDAO.createAccount(account);
        log.debug("Created account '{}'", account.getAccountId());

        balanceService.createBalances(account.getAccountId(), form.getCurrencies());
        log.debug("Created balances for currencies '{}'", form.getCurrencies());

        return toDTO(account);
    }

    private AccountDTO toDTO(Account existingAccount) {
        return new AccountDTO(
                existingAccount.getAccountId(),
                existingAccount.getCustomerId(),
                balanceService.findBalancesByAccountId(existingAccount.getAccountId())
        );
    }

    private List<AccountDTO> toDTO(List<Account> accounts) {
        List<AccountDTO> dtos = new ArrayList<>();
        accounts.forEach(account -> dtos.add(toDTO(account)));
        return dtos;
    }

}
