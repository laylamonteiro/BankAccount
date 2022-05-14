package com.laylamonteiro.bankaccount.controller;

import com.laylamonteiro.bankaccount.dto.request.TransactionForm;
import com.laylamonteiro.bankaccount.dto.response.TransactionDTO;
import com.laylamonteiro.bankaccount.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/transaction")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @GetMapping(path = "/")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionDTO> getAllTransactions() {
        log.info("Received request to list all transactions.");
        return service.findAll();
    }

    @GetMapping(path = "/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionDTO> getAllTransactionsByAccountId(@PathVariable("accountId") final Long accountId) {
        log.info("Received request to list all transactions for accountId '{}'.", accountId);
        return service.findAllByAccountId(accountId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDTO createTransaction(@Valid @RequestBody final TransactionForm form) throws NotFoundException {
        log.info("Received request to create transaction. Payload '{}'", form);
        return service.create(form);
    }
}
