package com.pachico.arithmetic.adapter.out.rest.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class PercentageResponse {
    private BigDecimal percentage;
}
