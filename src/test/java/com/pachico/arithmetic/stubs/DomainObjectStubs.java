package com.pachico.arithmetic.stubs;

import com.pachico.arithmetic.domain.Operation;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class DomainObjectStubs {

    public static UUID uuid = UUID.fromString("99d90ece-3a5d-48c5-b5d2-80cdb9f3c5d9");

    public static Operation getOperation() {
        return getOperation(uuid);
    }

    public static Operation getOperation(UUID uuid) {
        return Operation.builder()
                .id(uuid)
                .uri(WebStubs.uri)
                .request(WebStubs.requestBody)
                .response(WebStubs.responseBody)
                .method(WebStubs.method)
                .datetime(OffsetDateTime.now())
                .build();
    }

    public static List<Operation> getOperations() {
        return Arrays.asList(
                getOperation(UUID.randomUUID()),
                getOperation(UUID.randomUUID()),
                getOperation(UUID.randomUUID()),
                getOperation(UUID.randomUUID()));
    }
}
