package com.pachico.arithmetic.adapter.out.rest;

import com.pachico.arithmetic.adapter.out.rest.model.PercentageResponse;
import com.pachico.arithmetic.stubs.SharedStubs;
import com.pachico.arithmetic.stubs.WebStubs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class PercentageClientTest {

    @Mock
    private RestTemplate mockRestTemplate;

    private PercentageClient percentageClient;

    @BeforeEach
    public void setUp() {
        String baseUrl = "base_url";
        MockitoAnnotations.openMocks(this);
        percentageClient = new PercentageClient(mockRestTemplate, baseUrl);
    }

    @Test
    public void testExecute_Success() {
        // Prepare the test data
        BigDecimal x = BigDecimal.valueOf(10);
        BigDecimal y = BigDecimal.valueOf(20);
        BigDecimal mockPercentage = SharedStubs.percentage;

        // Given
        when(mockRestTemplate.postForObject(anyString(), any(HttpEntity.class), eq(PercentageResponse.class)))
                .thenReturn(WebStubs.getPercentageResponse());

        // When
        BigDecimal result = percentageClient.execute(x, y);

        // Then
        assertEquals(mockPercentage, result);

        verify(mockRestTemplate).postForObject(anyString(), any(HttpEntity.class), eq(PercentageResponse.class));

    }
}
