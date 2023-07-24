package com.pachico.arithmetic.shared.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IdProvider {

    public UUID provide() {
        return UUID.randomUUID();
    }
}
