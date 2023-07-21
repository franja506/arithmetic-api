package com.pachico.arithmetic.shared.error.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class ApiError {
    private String resource;
    private String message;
    private Map<String, String> metadata;
}