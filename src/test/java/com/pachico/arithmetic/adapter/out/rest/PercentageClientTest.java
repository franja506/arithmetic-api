package com.pachico.arithmetic.adapter.out.rest;

import com.pachico.arithmetic.adapter.out.rest.model.PercentageResponse;
import com.pachico.arithmetic.stubs.SharedStubs;
import com.pachico.arithmetic.stubs.WebStubs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// TODO: Terminar este test
public class PercentageClientTest {

    @Mock
    private RestTemplate mockRestTemplate;

    @InjectMocks
    private PercentageClient percentageClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute_Success() {
        // Prepare the test data
        BigDecimal x = BigDecimal.valueOf(10);
        BigDecimal y = BigDecimal.valueOf(20);
        BigDecimal mockPercentage = SharedStubs.percentage;

        // Mock the RestTemplate behavior
        when(mockRestTemplate.postForObject(any(), any(), any())).thenReturn(WebStubs.getPercentageResponse());

        // Call the execute method
        BigDecimal result = percentageClient.execute(x, y);

        // Verify that the RestTemplate.postForObject method was called with the expected arguments
        verify(mockRestTemplate).postForObject(any(), any(), eq(PercentageResponse.class));

        // Verify the result
        assertEquals(mockPercentage, result);

        // Verify that lastPercentage was set correctly
        //assertEquals(mockPercentage, percentageClient);
    }

    @Test
    public void testExecute_Failure() {
        // Prepare the test data
        BigDecimal x = BigDecimal.valueOf(10);
        BigDecimal y = BigDecimal.valueOf(20);

        // Mock the RestTemplate behavior to throw an exception
        when(mockRestTemplate.postForObject(any(), any(), any())).thenThrow(new RuntimeException("Some error"));

        // Call the execute method and expect a RuntimeException
        assertThrows(RuntimeException.class, () -> percentageClient.execute(x, y));

        // Verify that lastPercentage remains unchanged
        //assertEquals(null, percentageClient.getLastPercentage());
    }
}
