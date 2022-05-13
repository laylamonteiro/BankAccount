package com.laylamonteiro.bankaccount.controller;

import com.laylamonteiro.bankaccount.dto.request.AccountForm;
import com.laylamonteiro.bankaccount.dto.response.AccountDTO;
import com.laylamonteiro.bankaccount.entity.Account;
import com.laylamonteiro.bankaccount.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/account")
public class AccountController {

    @Autowired
    private AccountService service;

    @GetMapping(path = "/")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDTO> getAllAccounts() {
        log.info("Received request to list all accounts.");
        return service.findAll();
    }

    @GetMapping(path = "/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDTO getAccountById(@PathVariable("accountId") final String accountId) {
        log.info("Received request to list accountId '{}'.", accountId);
        final Account account = service.findByAccountId(accountId);
        return new AccountDTO(account);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@Valid @RequestBody final AccountForm form) {
        log.info("Received request to create account. Payload '{}'", form);
        service.create(form);
    }
}
