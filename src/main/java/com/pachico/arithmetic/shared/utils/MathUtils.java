package com.pachico.arithmetic.shared.utils;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

@Slf4j
public class MathUtils {

    private static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);

    public static BigDecimal mapToPercentage(BigDecimal percentage) {
        BigDecimal result = ONE_HUNDRED.add(percentage).scaleByPowerOfTen(-2).setScale(4, HALF_UP);

        log.info("percentage return: {}", result);
        return result;
    }
}
