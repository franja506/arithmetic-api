package com.pachico.arithmetic.application.usecase;

import com.pachico.arithmetic.application.port.in.GetSumPercentagePortIn;
import com.pachico.arithmetic.application.port.out.RetrievePercentagePortOut;
import com.pachico.arithmetic.shared.utils.MathUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@Slf4j
public class GetSumPercentageUseCase implements GetSumPercentagePortIn {

    private final RetrievePercentagePortOut percentageService;

    @Autowired
    public GetSumPercentageUseCase(RetrievePercentagePortOut percentageService) {
        this.percentageService = percentageService;
    }
    @Override
    public BigDecimal execute(BigDecimal x, BigDecimal y) {
        BigDecimal percentage = retrievePercentage(x,y);

        return applyPercentage(x.add(y), percentage);
    }

    private BigDecimal retrievePercentage(BigDecimal x, BigDecimal y) {
        log.info("retrieve percentage with {} and {}", x, y);
        BigDecimal percentage =  percentageService.execute(x,y);
        log.info("percentage retrieve: {}", percentage);

        return percentage;
    }

    private BigDecimal applyPercentage(BigDecimal num, BigDecimal percentage) {
        return MathUtils.mapToPercentage(percentage).multiply(num).setScale(4, RoundingMode.HALF_UP);
    }
}
