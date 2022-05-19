package com.laylamonteiro.bankaccount.service;

import com.laylamonteiro.bankaccount.dao.TransactionDAO;
import com.laylamonteiro.bankaccount.dto.request.TransactionForm;
import com.laylamonteiro.bankaccount.dto.response.CreateTransactionDTO;
import com.laylamonteiro.bankaccount.dto.response.TransactionDTO;
import com.laylamonteiro.bankaccount.entity.Balance;
import com.laylamonteiro.bankaccount.entity.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionDAO transactionDAO;

    @Mock
    private BalanceService balanceService;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    void findAll() {
        Transaction transactions = new Transaction();
        when(transactionDAO.findAll()).thenReturn(List.of(transactions));
        when(balanceService.findBalancesByAccountId(anyLong())).thenReturn(List.of(new Balance()));

        List<TransactionDTO> transactionss = transactionService.findAll();
        assertThat(transactionss).isNotEmpty();
    }

    @Test
    void findAllByAccountId() {
        Transaction transaction = new Transaction();
        when(transactionDAO.findAllByAccountId(anyLong())).thenReturn(List.of(transaction));

        List<TransactionDTO> transactionDTO = transactionService.findAllByAccountId(1L);
        assertThat(transactionDTO).isNotEmpty();
    }

    @Test
    void findAllByAccountId_EmptyExistingTransactions() {
        when(transactionDAO.findAllByAccountId(anyLong())).thenReturn(Collections.emptyList());

        List<TransactionDTO> transactionDTO = transactionService.findAllByAccountId(1L);
        assertThat(transactionDTO).isEmpty();
    }

    @Test
    void createIncomingTransaction() {
        TransactionForm transactionForm = new TransactionForm(1L, BigDecimal.ZERO, "EUR", "IN", "Test");
        Balance balance = new Balance(1L, BigDecimal.ZERO, "EUR");

        doNothing().when(transactionDAO).createTransaction(any(Transaction.class));
        when(balanceService.createBalances(anyLong(), anyList())).thenReturn(List.of(balance));
        doNothing().when(balanceService).updateBalance(any(Balance.class));
        when(balanceService.findBalancesByAccountId(anyLong())).thenReturn(List.of(balance));

        CreateTransactionDTO createdTransaction = transactionService.create(transactionForm);
        assertThat(createdTransaction).isNotNull();
    }

    @Test
    void createOutgoingTransaction() {
        TransactionForm transactionForm = new TransactionForm(1L, BigDecimal.ZERO, "EUR", "OUT", "Test");
        Balance balance = new Balance(1L, BigDecimal.ZERO, "EUR");

        doNothing().when(transactionDAO).createTransaction(any(Transaction.class));
        when(balanceService.createBalances(anyLong(), anyList())).thenReturn(List.of(balance));
        doNothing().when(balanceService).updateBalance(any(Balance.class));
        when(balanceService.findBalancesByAccountId(anyLong()))
                .thenReturn(Collections.emptyList())
                .thenReturn(List.of(balance));

        CreateTransactionDTO createdTransaction = transactionService.create(transactionForm);
        assertThat(createdTransaction).isNotNull();
    }

    @Test
    void createOutgoingTransaction_NoPreviousTransactions() {
        TransactionForm transactionForm = new TransactionForm(1L, BigDecimal.ZERO, "EUR", "OUT", "Test");
        Balance balance = new Balance(1L, BigDecimal.ZERO, "USD");

        doNothing().when(transactionDAO).createTransaction(any(Transaction.class));
        when(balanceService.createBalances(anyLong(), anyList())).thenReturn(List.of(balance));
        doNothing().when(balanceService).updateBalance(any(Balance.class));
        when(balanceService.findBalancesByAccountId(anyLong()))
                .thenReturn(List.of(balance))
                .thenReturn(null);

        CreateTransactionDTO createdTransaction = transactionService.create(transactionForm);
        assertThat(createdTransaction).isNotNull();
    }

    @Test
    void transactionWithInsufficientFunds() {
        TransactionForm transactionForm = new TransactionForm(1L, BigDecimal.TEN, "EUR", "OUT", "Test");
        Balance balance = new Balance(1L, BigDecimal.ZERO, "EUR");

        doNothing().when(transactionDAO).createTransaction(any(Transaction.class));
        when(balanceService.createBalances(anyLong(), anyList())).thenReturn(List.of(balance));
        doNothing().when(balanceService).updateBalance(any(Balance.class));
        when(balanceService.findBalancesByAccountId(anyLong())).thenReturn(List.of(balance));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> transactionService.create(transactionForm))
                .withMessageContaining("Insufficient funds.");
        verify(transactionDAO, times(0)).createTransaction(any(Transaction.class));
    }

    @Test
    void transactionWithInvalidCurrency() {
        TransactionForm transactionForm = new TransactionForm(1L, BigDecimal.TEN, "BRL", "OUT", "Test");
        Balance balance = new Balance(1L, BigDecimal.ZERO, "EUR");

        doNothing().when(transactionDAO).createTransaction(any(Transaction.class));
        when(balanceService.createBalances(anyLong(), anyList())).thenReturn(List.of(balance));
        doNothing().when(balanceService).updateBalance(any(Balance.class));
        when(balanceService.findBalancesByAccountId(anyLong())).thenReturn(List.of(balance));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> transactionService.create(transactionForm))
                .withMessageContaining("Invalid currency.");
        verify(transactionDAO, times(0)).createTransaction(any(Transaction.class));
    }

    @Test
    void transactionWithInvalidDirection() {
        TransactionForm transactionForm = new TransactionForm(1L, BigDecimal.TEN, "EUR", "Test", "Test");
        Balance balance = new Balance(1L, BigDecimal.ZERO, "EUR");

        doNothing().when(transactionDAO).createTransaction(any(Transaction.class));
        when(balanceService.createBalances(anyLong(), anyList())).thenReturn(List.of(balance));
        doNothing().when(balanceService).updateBalance(any(Balance.class));
        when(balanceService.findBalancesByAccountId(anyLong())).thenReturn(List.of(balance));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> transactionService.create(transactionForm))
                .withMessageContaining("Invalid transaction direction.");
        verify(transactionDAO, times(0)).createTransaction(any(Transaction.class));
    }

    @Test
    void transactionWithNegativeTransactionAmount() {
        TransactionForm transactionForm = new TransactionForm(1L, BigDecimal.valueOf(-10), "EUR", "OUT", "Test");
        Balance balance = new Balance(1L, BigDecimal.ZERO, "EUR");

        doNothing().when(transactionDAO).createTransaction(any(Transaction.class));
        when(balanceService.createBalances(anyLong(), anyList())).thenReturn(List.of(balance));
        doNothing().when(balanceService).updateBalance(any(Balance.class));
        when(balanceService.findBalancesByAccountId(anyLong())).thenReturn(List.of(balance));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> transactionService.create(transactionForm))
                .withMessageContaining("Invalid amount.");
        verify(transactionDAO, times(0)).createTransaction(any(Transaction.class));
    }
}