package com.pachico.arithmetic.shared.error.model.ApplicationErrors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract sealed class ApplicationError permits MessageNotReadable, MethodArgumentNotValid, ServerError {
    private HttpStatus status;
    private String message;
    private Throwable origin;
}