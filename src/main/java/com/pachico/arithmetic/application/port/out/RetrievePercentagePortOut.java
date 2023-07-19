package com.pachico.arithmetic.application.port.out;

import java.math.BigDecimal;

public interface RetrievePercentagePortOut {
    BigDecimal execute(BigDecimal x, BigDecimal y);
}
