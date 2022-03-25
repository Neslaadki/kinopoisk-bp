package com.example.kinopoiskbmp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Wrong email format")
public class BadEmailValue extends RuntimeException{

    public BadEmailValue(String message) {
        super(message);
    }
}
