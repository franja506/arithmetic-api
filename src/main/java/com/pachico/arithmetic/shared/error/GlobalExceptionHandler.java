package com.pachico.arithmetic.shared.error;

import com.pachico.arithmetic.shared.error.model.PercentageApiNotAvailableException;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(BAD_REQUEST, "Validation failed for request");
        problemDetail.setTitle("Bad Request");
        problemDetail.setProperty("errors", errors);
        return new ResponseEntity<>(problemDetail, BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex){

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(BAD_REQUEST, ex.getLocalizedMessage());
        problemDetail.setTitle("Bad Request");
        return new ResponseEntity<>(problemDetail, BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception ex){

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
        problemDetail.setTitle("Internal Server Error");
        return new ResponseEntity<>(problemDetail, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PercentageApiNotAvailableException.class)
    protected ResponseEntity<Object> handlePercentageApiNotAvailable(PercentageApiNotAvailableException ex){

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(INTERNAL_SERVER_ERROR, "Percentage api not available");
        problemDetail.setTitle("Internal Server Error");
        return new ResponseEntity<>(problemDetail, INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(RequestNotPermitted.class)
    protected ResponseEntity<Object> handleRequestNotPermitted(RequestNotPermitted ex){

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(TOO_MANY_REQUESTS,  "You have exhausted your API Request Quota");
        problemDetail.setTitle("Too Many Requests");
        return new ResponseEntity<>(problemDetail, TOO_MANY_REQUESTS);
    }
}
