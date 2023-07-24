package com.pachico.arithmetic.adapter.in.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
public class OperationRequest {

    @Schema(name = "number1", example = "25.00")
    @NotNull(message = "number1 cannot be null")
    @Digits(integer = 12, fraction = 2,
            message = "The total number of digits must not exceed 14 (12 integer + 2 fraction)")
    public BigDecimal number1;

    @Schema(name = "number2", example = "10.00")
    @NotNull(message = " number2 cannot be null")
    @Digits(integer = 12, fraction = 2,
            message = "The total number of digits must not exceed 14 (12 integer + 2 fraction)")
    public BigDecimal number2;
}