package com.pachico.arithmetic.application.usecase;

import com.pachico.arithmetic.application.port.out.RetrievePercentagePortOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GetSumPercentageUseCaseTest {

    @Mock
    private RetrievePercentagePortOut percentageService;

    @InjectMocks
    private GetSumPercentageUseCase getSumPercentageUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @ArgumentsSource(NestedArgumentsProvider.class)
    public void testExecute_WithValidPercentages_ShouldReturnCorrectResult(BigDecimal x, BigDecimal y, BigDecimal percentage, BigDecimal expected) {

        // Mock the percentageService to return the expected percentage
        when(percentageService.execute(x, y)).thenReturn(percentage);

        // Act
        BigDecimal result = getSumPercentageUseCase.execute(x, y);

        // Assert
        assertEquals(expected.setScale(4, RoundingMode.HALF_UP), result, "Incorrect result with valid percentages");

        // Verify that the percentageService.execute method was called with the correct arguments
        verify(percentageService, times(1)).execute(x, y);
    }

    static class NestedArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(
                ExtensionContext context) {

            return Stream.of(
                    Arguments.of(BigDecimal.valueOf(12), BigDecimal.valueOf(13), BigDecimal.valueOf(25), BigDecimal.valueOf(31.25)),
                    Arguments.of(BigDecimal.valueOf(5), BigDecimal.valueOf(5), BigDecimal.valueOf(10), BigDecimal.valueOf(11)),
                    Arguments.of(BigDecimal.valueOf(10.25), BigDecimal.valueOf(1), BigDecimal.valueOf(11.25), BigDecimal.valueOf(12.5156)),
                    Arguments.of(BigDecimal.valueOf(23.03), BigDecimal.valueOf(17.12), BigDecimal.valueOf(40.15), BigDecimal.valueOf(56.2702)),
                    Arguments.of(BigDecimal.valueOf(-5.25), BigDecimal.valueOf(-4.75), BigDecimal.valueOf(-10), BigDecimal.valueOf(-9)),
                    Arguments.of(BigDecimal.valueOf(6.12), BigDecimal.valueOf(-31.03), BigDecimal.valueOf(-24.91), BigDecimal.valueOf(-18.7049)),
                    Arguments.of(BigDecimal.valueOf(6.12), BigDecimal.valueOf(-17.01), BigDecimal.valueOf(-10.89), BigDecimal.valueOf(-9.7041)),
                    Arguments.of(BigDecimal.valueOf(7.02), BigDecimal.valueOf(-16.01), BigDecimal.valueOf(-8.99), BigDecimal.valueOf(-8.1818)),
                    Arguments.of(BigDecimal.valueOf(100), BigDecimal.valueOf(50), BigDecimal.valueOf(0), BigDecimal.valueOf(150))
            );
        }
    }
}
