package com.laylamonteiro.bankaccount.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laylamonteiro.bankaccount.dto.request.TransactionForm;
import com.laylamonteiro.bankaccount.dto.response.CreateTransactionDTO;
import com.laylamonteiro.bankaccount.dto.response.TransactionDTO;
import com.laylamonteiro.bankaccount.entity.Balance;
import com.laylamonteiro.bankaccount.entity.Transaction;
import com.laylamonteiro.bankaccount.mapper.AccountMapper;
import com.laylamonteiro.bankaccount.mapper.BalanceMapper;
import com.laylamonteiro.bankaccount.mapper.TransactionMapper;
import com.laylamonteiro.bankaccount.messaging.MessagePublisher;
import com.laylamonteiro.bankaccount.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @MockBean
    private TransactionService transactionService;

    @MockBean
    private MessagePublisher messagePublisher;

    @MockBean
    private AccountMapper accountMapper;

    @MockBean
    private BalanceMapper balanceMapper;

    @MockBean
    private TransactionMapper transactionMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllTransactions() throws Exception {
        Transaction transaction = new Transaction();
        TransactionDTO transactionDTO = new TransactionDTO();

        when(transactionMapper.findAllTransactions())
                .thenReturn(Collections.singletonList(transaction));
        when(transactionService.findAll())
                .thenReturn(Collections.singletonList(transactionDTO));


        mockMvc.perform(get("/transaction/")).andDo(print());
        mockMvc.perform(get("/transaction/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void getAllTransactionsByAccountId() throws Exception {
        Transaction transaction = new Transaction(1L, 1L, BigDecimal.ZERO, "EUR", "IN", "Test");
        TransactionDTO transactionDTO = new TransactionDTO(1L, 1L, BigDecimal.ZERO, "EUR", "IN", "Test");

        when(transactionMapper.findTransactionsByAccountId(1L))
                .thenReturn(Collections.singletonList(transaction));
        when(transactionService.findAllByAccountId(1L))
                .thenReturn(Collections.singletonList(transactionDTO));

        mockMvc.perform(get("/transaction/1/transactions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void createTransaction() throws Exception {
        CreateTransactionDTO createTransactionDTO = new CreateTransactionDTO(1L, 1L, BigDecimal.ZERO, "EUR", "IN", "Test", List.of(new Balance()));
        TransactionForm transactionForm = new TransactionForm(1L, BigDecimal.ZERO, "EUR", "IN", "Test");
        var objectWriter = objectMapper.writerWithDefaultPrettyPrinter();

        when(transactionService.create(transactionForm)).thenReturn(createTransactionDTO);
        doNothing().when(messagePublisher).publish(createTransactionDTO);
        mockMvc.perform(post("/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(transactionForm)))
                .andExpect(status().isCreated());
    }
}