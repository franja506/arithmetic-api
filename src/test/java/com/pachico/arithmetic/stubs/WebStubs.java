package com.pachico.arithmetic.stubs;

import com.pachico.arithmetic.adapter.in.controller.model.OperationResponse;
import com.pachico.arithmetic.adapter.out.rest.model.PercentageResponse;

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

    public static OperationResponse getOperationResponse() {
        return new OperationResponse(SharedStubs.percentage);
    }
}
