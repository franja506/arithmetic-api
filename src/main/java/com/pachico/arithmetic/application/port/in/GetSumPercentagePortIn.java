package com.pachico.arithmetic.application.port.in;

import java.math.BigDecimal;

public interface GetSumPercentagePortIn {
    BigDecimal execute(BigDecimal x, BigDecimal y);
}
