package com.laylamonteiro.bankaccount.controller;

import com.laylamonteiro.bankaccount.dto.request.AccountForm;
import com.laylamonteiro.bankaccount.dto.response.AccountDTO;
import com.laylamonteiro.bankaccount.dto.response.TransactionDTO;
import com.laylamonteiro.bankaccount.model.Account;
import com.laylamonteiro.bankaccount.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/account")
public class AccountController {

    @Autowired
    private AccountService service;

    @GetMapping(path = "/")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionDTO> getAllAccounts() {
        log.info("Received request to list all accounts.");
        final List<Account> account = service.findAll();
        return Collections.emptyList();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDTO getAccountById(@PathVariable("id") final Long id) {
        log.info("Received request to list accountId '{}'.", id);
        final Account account = service.findById(id);
        return new AccountDTO(account);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@Valid @RequestBody final AccountForm form) {
        log.info("Received request to create account. Payload '{}'", form);
        service.create(form);
    }
}
