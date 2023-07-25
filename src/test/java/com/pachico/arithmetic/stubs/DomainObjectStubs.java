package com.pachico.arithmetic.stubs;

import com.pachico.arithmetic.domain.Operation;

import java.time.OffsetDateTime;
import java.util.UUID;

public class DomainObjectStubs {

    public static UUID uuid = UUID.fromString("99d90ece-3a5d-48c5-b5d2-80cdb9f3c5d9");

    public static Operation getOperation() {
        return Operation.builder()
                .id(uuid)
                .uri(WebStubs.uri)
                .request(WebStubs.requestBody)
                .response(WebStubs.responseBody)
                .method(WebStubs.method)
                .datetime(OffsetDateTime.now())
                .build();
    }
}
