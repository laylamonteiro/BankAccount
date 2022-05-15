package com.laylamonteiro.bankaccount.service;

import com.laylamonteiro.bankaccount.dao.BalanceDAO;
import com.laylamonteiro.bankaccount.entity.Balance;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
class BalanceServiceTest {

    @Mock
    private BalanceDAO balanceDAO;

    @InjectMocks
    private BalanceService balanceService;

    @Test
    void findAllBalances() {
        Balance balance = new Balance();
        when(balanceDAO.findAll()).thenReturn(List.of(balance));

        List<Balance> balanceDTO = balanceService.findAllBalances();
        assertThat(balanceDTO).isNotNull();
    }

    @Test
    void findBalancesByBalanceId() {
        Balance balance = new Balance();
        when(balanceDAO.findBalancesByAccountId(anyLong())).thenReturn(List.of(balance));

        List<Balance> balanceDTO = balanceService.findBalancesByAccountId(1L);
        assertThat(balanceDTO).isNotNull();
    }

    @Test
    void createBalances() {
        doNothing().when(balanceDAO).createBalance(any(Balance.class));

        balanceService.createBalances(1L, List.of("EUR", "USD"));

        verify(balanceDAO, times(2)).createBalance(any(Balance.class));
    }

    @Test
    void createBalances_CurrencyNotAllowed() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> balanceService.createBalances(1L, List.of("XXX", "YYY")))
                .withMessageContaining("Invalid currency.");

        verify(balanceDAO, times(0)).createBalance(any(Balance.class));
    }

    @Test
    void updateBalance() {
        doNothing().when(balanceDAO).updateBalance(any(Balance.class));

        Balance balance = new Balance();
        balanceService.updateBalance(balance);

        verify(balanceDAO, times(1)).updateBalance(any(Balance.class));
    }
}