package com.pachico.arithmetic.shared.error;

import com.pachico.arithmetic.shared.error.model.ApiErrorResponse;
import com.pachico.arithmetic.shared.error.model.ApplicationErrors.ApplicationError;
import com.pachico.arithmetic.shared.error.model.ApplicationErrors.MessageNotReadable;
import com.pachico.arithmetic.shared.error.model.ApplicationErrors.MethodArgumentNotValid;
import com.pachico.arithmetic.shared.error.model.ApplicationErrors.ServerError;
import com.pachico.arithmetic.shared.error.provider.ErrorResponseProvider;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ErrorHandler {

    private final ErrorResponseProvider errorResponseProvider;

    @Autowired
    public ErrorHandler(ErrorResponseProvider errorResponseProvider) {
        this.errorResponseProvider = errorResponseProvider;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity<ApiErrorResponse> handleMessageNotReadable(Throwable ex) {
        val entity = asResponse(new MessageNotReadable(ex));
        log.error("message not readable error handled: {}", entity);

        return entity;
    }

    @Order
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handle(Throwable ex) {
        return doHandle(new ServerError(ex));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        return doHandle(new MethodArgumentNotValid(ex));

    }
/*    private List<ApiError> buildErrors(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<ApiError> errors = new ArrayList<>();

        for (FieldError error : result.getFieldErrors()) {
            errors.add(new ApiError(error.getDefaultMessage()));
        }

        return errors;
    }*/

    private ResponseEntity<ApiErrorResponse> asResponse(ApplicationError ae) {
        val response = errorResponseProvider.provideFor(ae);

        return new ResponseEntity<>(response, ae.getStatus());
    }

    private ResponseEntity<ApiErrorResponse> doHandle(ApplicationError applicationError) {
        val entity = buildFor(applicationError);
        log.info("error handled: {}", entity);

        return entity;
    }
    private ResponseEntity<ApiErrorResponse> buildFor(ApplicationError error) {
        val response = errorResponseProvider.provideFor(error);

        return new ResponseEntity<>(response, error.getStatus());
    }
}
