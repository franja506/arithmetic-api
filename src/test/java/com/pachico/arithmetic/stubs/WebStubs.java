package com.pachico.arithmetic.stubs;

import com.pachico.arithmetic.adapter.in.controller.model.OperationRequest;
import com.pachico.arithmetic.adapter.in.controller.model.OperationResponse;
import com.pachico.arithmetic.adapter.out.rest.model.PercentageResponse;
import org.springframework.http.ProblemDetail;

import java.math.BigDecimal;
import java.net.URI;

import static org.springframework.http.HttpStatus.*;

public class WebStubs {

    public static String method = "GET";
    public static String uri = "/test";
    public static String requestBody = "Test Request Body";
    public static String responseBody = "Test Response Body";

    public static PercentageResponse getPercentageResponse() {
        PercentageResponse response =  new PercentageResponse();
        response.setPercentage(SharedStubs.percentage);

        return response;
    }

    public static OperationRequest getOperationRequest() {
        OperationRequest request = new OperationRequest();
        request.setNumber1(BigDecimal.valueOf(12));
        request.setNumber2(BigDecimal.valueOf(13));

        return request;
    }

    public static OperationResponse getOperationResponse() {
        return new OperationResponse(SharedStubs.percentage);
    }

    public static ProblemDetail getApiRateLimitResponse() {
        ProblemDetail response = ProblemDetail.forStatusAndDetail(TOO_MANY_REQUESTS,  "You have exhausted your API Request Quota");
        response.setTitle("Too Many Requests");
        response.setInstance(URI.create("/calculate"));

        return response;
    }

    public static ProblemDetail getInternalErrorResponse() {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(INTERNAL_SERVER_ERROR, "Error message");
        problemDetail.setTitle("Internal Server Error");
        problemDetail.setInstance(URI.create("/calculate"));

        return problemDetail;
    }

    public static ProblemDetail getHttpMessageNotReadableResponse() {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(BAD_REQUEST, "Validation failed for request");
        problemDetail.setTitle("Bad Request");
        problemDetail.setInstance(URI.create("/calculate"));

        return problemDetail;
    }




    public static ProblemDetail getPercentageApiNotExceptionResponse() {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(INTERNAL_SERVER_ERROR, "Percentage api not available");
        problemDetail.setTitle("Internal Server Error");
        problemDetail.setInstance(URI.create("/calculate"));

        return problemDetail;
    }
}
