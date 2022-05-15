package com.laylamonteiro.bankaccount.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laylamonteiro.bankaccount.dto.request.AccountForm;
import com.laylamonteiro.bankaccount.dto.response.AccountDTO;
import com.laylamonteiro.bankaccount.entity.Account;
import com.laylamonteiro.bankaccount.entity.Balance;
import com.laylamonteiro.bankaccount.mapper.AccountMapper;
import com.laylamonteiro.bankaccount.mapper.BalanceMapper;
import com.laylamonteiro.bankaccount.mapper.TransactionMapper;
import com.laylamonteiro.bankaccount.messaging.MessagePublisher;
import com.laylamonteiro.bankaccount.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @MockBean
    private AccountService accountService;

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
    void getAllAccounts() throws Exception {
        Account account = new Account();
        AccountDTO accountDTO = new AccountDTO();

        when(accountMapper.findAllAccounts())
                .thenReturn(Collections.singletonList(account));
        when(accountService.findAll())
                .thenReturn(Collections.singletonList(accountDTO));


        mockMvc.perform(get("/account/")).andDo(print());
        mockMvc.perform(get("/account/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void getAccountById() throws Exception {
        Account account = new Account(1L, 123L, "Test");
        AccountDTO accountDTO = new AccountDTO(1L, 123L, Collections.singletonList(new Balance()));

        when(accountMapper.findAccountByAccountId(1L))
                .thenReturn(account);
        when(accountService.findByAccountId(1L))
                .thenReturn(accountDTO);

        mockMvc.perform(get("/account/1")).andDo(print());
        mockMvc.perform(get("/account/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId", is(1)));
    }

    @Test
    void createAccount() throws Exception {
        AccountDTO accountDTO = new AccountDTO(1L, 123L, Collections.singletonList(new Balance()));
        AccountForm accountForm = new AccountForm(1L, "Test", List.of("EUR"));
        var objectWriter = objectMapper.writerWithDefaultPrettyPrinter();

        when(accountService.create(accountForm)).thenReturn(accountDTO);
        doNothing().when(messagePublisher).publish(accountDTO);
        mockMvc.perform(post("/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(accountForm)))
                .andExpect(status().isCreated());

    }
}