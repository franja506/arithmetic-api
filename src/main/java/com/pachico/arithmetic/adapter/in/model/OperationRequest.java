package com.pachico.arithmetic.adapter.in.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
public class OperationRequest {

    @NotNull(message = " x cannot be null")
    public BigDecimal x;
    @NotNull(message = " y cannot be null")
    public BigDecimal y;
}