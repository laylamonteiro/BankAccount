package com.laylamonteiro.bankaccount.exception;

import lombok.Getter;

@Getter
public class UnprocessableEntityException extends RuntimeException {

    private static final long serialVersionUID = 6843671149518121052L;

    public UnprocessableEntityException(final String message) {
        super(message);
    }
}
