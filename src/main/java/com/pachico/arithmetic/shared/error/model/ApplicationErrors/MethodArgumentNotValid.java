package com.pachico.arithmetic.shared.error.model.ApplicationErrors;

import lombok.Data;
import lombok.EqualsAndHashCode;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@EqualsAndHashCode(callSuper = true)
@Data
public final class MethodArgumentNotValid extends ApplicationError{

    public MethodArgumentNotValid(Throwable ex) {
        super(BAD_REQUEST,null,ex);
    }
}
