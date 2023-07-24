package com.pachico.arithmetic.adapter.in.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationResponse {

    @Schema(example = "10.25", description = "A result of operation. Is a decimal number with two decimals.")
    private BigDecimal result;
}
