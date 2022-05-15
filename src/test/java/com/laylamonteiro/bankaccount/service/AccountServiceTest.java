package com.laylamonteiro.bankaccount.service;

import com.laylamonteiro.bankaccount.dao.AccountDAO;
import com.laylamonteiro.bankaccount.dto.request.AccountForm;
import com.laylamonteiro.bankaccount.dto.response.AccountDTO;
import com.laylamonteiro.bankaccount.entity.Account;
import com.laylamonteiro.bankaccount.entity.Balance;
import org.apache.ibatis.javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
class AccountServiceTest {

    @Mock
    private AccountDAO accountDAO;

    @Mock
    private BalanceService balanceService;

    @InjectMocks
    private AccountService accountService;

    @Test
    void findAllAccounts() {
        Account account = new Account();
        when(accountDAO.findAll()).thenReturn(List.of(account));
        when(balanceService.findBalancesByAccountId(anyLong())).thenReturn(List.of(new Balance()));

        List<AccountDTO> accounts = accountService.findAll();
        assertThat(accounts).isNotEmpty();
    }

    @Test
    void findByAccountId() throws NotFoundException {
        Account account = new Account();
        when(accountDAO.findByAccountId(anyLong())).thenReturn(account);

        AccountDTO accountDTO = accountService.findByAccountId(1L);
        assertThat(accountDTO).isNotNull();
    }

    @Test
    void findByAccountId_AccountNotFound() {
        when(accountDAO.findByAccountId(anyLong())).thenReturn(null);

        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> accountService.findByAccountId(1L));
    }

    @Test
    void create() {
        AccountForm accountForm = new AccountForm(1L, "Test", List.of("EUR"));

        doNothing().when(accountDAO).createAccount(any(Account.class));
        when(balanceService.createBalances(anyLong(), anyList())).thenReturn(List.of(new Balance()));
        when(balanceService.findBalancesByAccountId(anyLong())).thenReturn(List.of(new Balance()));

        AccountDTO createdAccount = accountService.create(accountForm);
        assertThat(createdAccount).isNotNull();
    }
}