package com.pachico.arithmetic.shared.error.model.ApplicationErrors;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@EqualsAndHashCode(callSuper = true)
@Getter
@Data
public final class ServerError extends ApplicationError {

    public ServerError(Throwable origin) {
        super(INTERNAL_SERVER_ERROR,"internal server error",origin);
    }
}
