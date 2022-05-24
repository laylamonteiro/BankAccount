package com.laylamonteiro.bankaccount.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerConfig {

    @Getter
    public static class Errors {
        private final String errorMessage;

        public Errors(String message) {
            this.errorMessage = message;
        }
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public Errors handleNotFoundException(final EntityNotFoundException exception) {
        return new Errors(exception.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(UnprocessableEntityException.class)
    public Errors handleUnprocessableEntityException(final UnprocessableEntityException exception) {
        return new Errors(exception.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public Errors handleUnexpectedException(final RuntimeException exception) {
        return new Errors("Unexpected error." + exception.getMessage());
    }

}
