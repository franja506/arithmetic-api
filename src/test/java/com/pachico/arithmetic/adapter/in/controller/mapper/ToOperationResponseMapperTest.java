package com.pachico.arithmetic.adapter.in.controller.mapper;

import com.pachico.arithmetic.adapter.in.controller.model.OperationResponse;
import com.pachico.arithmetic.stubs.SharedStubs;
import com.pachico.arithmetic.stubs.WebStubs;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToOperationResponseMapperTest {

    private final ToOperationResponseMapper mapper = new ToOperationResponseMapper();

    @Test
    @DisplayName("Test mapping BigDecimal to OperationResponse")
    public void successfulMapping() {
        OperationResponse result = WebStubs.getOperationResponse();

        // When
        OperationResponse response = mapper.mapFrom(SharedStubs.percentage);

        // Then
        assertEquals(result, response);
    }
}
