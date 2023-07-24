package com.pachico.arithmetic.shared.utils;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathUtilsTest {

    @ParameterizedTest
    @ArgumentsSource(NestedArgumentsProvider.class)
    public void mapToPercentage_ShouldReturnCorrectPercentage(BigDecimal inputPercentage, BigDecimal expected) {
        // When
        BigDecimal result = MathUtils.mapToPercentage(inputPercentage);

        // Then
        assertEquals(expected, result, "Incorrect result for mapping percentage");
    }

    static class NestedArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(
                ExtensionContext context) {

            return Stream.of(
                    Arguments.of(new BigDecimal("0.25"), new BigDecimal("1.0025")),
                    Arguments.of(new BigDecimal("50"), new BigDecimal("1.5000")),
                    Arguments.of(new BigDecimal("0"), new BigDecimal("1.0000")),
                    Arguments.of(new BigDecimal("-10") , new BigDecimal("0.9000")),
                    Arguments.of(new BigDecimal("-0.25"), new BigDecimal("0.9975"))
            );
        }
    }
}