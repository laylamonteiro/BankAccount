package com.laylamonteiro.bankaccount.exception;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -4582163719461435189L;

    public EntityNotFoundException(String message) {
        super(message);
    }
}
