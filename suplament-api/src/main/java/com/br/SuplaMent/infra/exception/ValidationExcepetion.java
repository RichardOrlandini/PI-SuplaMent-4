package com.br.SuplaMent.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationExcepetion extends RuntimeException {

    public ValidationExcepetion(String message) {
        super(message);
    }
}
