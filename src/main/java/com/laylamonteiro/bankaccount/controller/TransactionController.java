package com.laylamonteiro.bankaccount.controller;

import com.laylamonteiro.bankaccount.dto.request.TransactionForm;
import com.laylamonteiro.bankaccount.dto.response.TransactionDTO;
import com.laylamonteiro.bankaccount.model.Transaction;
import com.laylamonteiro.bankaccount.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path = "/transaction")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @GetMapping(path = "/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionDTO getAllTransactionsByAccountId(@PathVariable("accountId") final Long accountId) {
        log.info("Received request to list all transactions for accountId '{}'.", accountId);
        final Transaction transaction = service.findAllByAccountId(accountId);
        return new TransactionDTO(transaction);
    }

    @GetMapping(path = "/{transactionId}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionDTO getTransactionById(@PathVariable("transactionId") final Long transactionId) {
        log.info("Received request to list transactionId '{}'.", transactionId);
        final Transaction transaction = service.findById(transactionId);
        return new TransactionDTO(transaction);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTransaction(@Valid @RequestBody final TransactionForm form) {
        log.info("Received request to create transaction. Payload '{}'", form);
        service.create(form);
    }
}
