package com.laylamonteiro.bankaccount.controller;

import com.laylamonteiro.bankaccount.dto.request.AccountForm;
import com.laylamonteiro.bankaccount.dto.response.AccountDTO;
import com.laylamonteiro.bankaccount.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
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
    public AccountDTO getAccountById(@PathVariable("accountId") final Long accountId) throws NotFoundException {
        log.info("Received request to list accountId '{}'.", accountId);
        return service.findByAccountId(accountId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO createAccount(@Valid @RequestBody final AccountForm form) {
        log.info("Received request to create account. Payload '{}'", form);
        return service.create(form);
    }
}
