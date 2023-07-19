package com.pachico.arithmetic.adapter.out.rest;

import com.pachico.arithmetic.application.port.out.RetrievePercentagePortOut;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class PercentageClient implements RetrievePercentagePortOut {
    @Override
    public BigDecimal execute(BigDecimal x, BigDecimal y) {
        return x.add(y).scaleByPowerOfTen(-2);
    }
}
