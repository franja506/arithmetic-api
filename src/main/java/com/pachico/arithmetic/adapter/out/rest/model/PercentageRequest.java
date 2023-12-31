package com.pachico.arithmetic.adapter.out.rest.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PercentageRequest {
    private BigDecimal number1;
    private BigDecimal number2;
}