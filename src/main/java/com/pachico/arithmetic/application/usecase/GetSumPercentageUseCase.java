package com.pachico.arithmetic.application.usecase;

import com.pachico.arithmetic.application.port.in.GetSumPercentagePortIn;
import com.pachico.arithmetic.application.port.out.RetrievePercentagePortOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class GetSumPercentageUseCase implements GetSumPercentagePortIn {

    private final RetrievePercentagePortOut percentageService;

    @Autowired
    public GetSumPercentageUseCase(RetrievePercentagePortOut percentageService) {
        this.percentageService = percentageService;
    }
    @Override
    public BigDecimal execute(BigDecimal x, BigDecimal y) {
        return x.add(y).multiply(retrievePercentage(x,y));
    }

    private BigDecimal retrievePercentage(BigDecimal x, BigDecimal y) {
        return percentageService.execute(x,y)
                .add(BigDecimal.ONE);
    }
}
