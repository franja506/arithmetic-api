package com.pachico.arithmetic.shared.error.model.ApplicationErrors;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public final class MessageNotReadable extends ApplicationError {

    public MessageNotReadable(Throwable origin) {
        super(BAD_REQUEST,null,origin);
    }
}
