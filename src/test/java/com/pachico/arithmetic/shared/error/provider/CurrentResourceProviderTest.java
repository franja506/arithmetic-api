package com.pachico.arithmetic.shared.error.provider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CurrentResourceProviderTest {

    @Mock
    private HttpServletRequest httpServletRequest;

    @InjectMocks
    private CurrentResourceProvider currentResourceProvider;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testProvideUri() {
        // Arrange
        String expectedUri = "/api/resource/123";
        when(httpServletRequest.getRequestURI()).thenReturn(expectedUri);

        // Act
        String providedUri = currentResourceProvider.provideUri();

        // Assert
        assertEquals(expectedUri, providedUri, "Incorrect URI provided");
        verify(httpServletRequest, times(1)).getRequestURI();
    }

    @Test
    public void testProvideUri_WithNoQueryString() {
        // Arrange
        String expectedUri = "/api/resource/456";
        when(httpServletRequest.getRequestURI()).thenReturn(expectedUri);

        // Act
        String providedUri = currentResourceProvider.provideUri();

        // Assert
        assertEquals(expectedUri, providedUri, "Incorrect URI provided");
        verify(httpServletRequest, times(1)).getRequestURI();
    }

    @Test
    public void testProvideQuery_WithQueryString() {
        // Arrange
        String expectedQueryString = "param1=value1&param2=value2";
        when(httpServletRequest.getQueryString()).thenReturn(expectedQueryString);

        // Act
        String providedQuery = currentResourceProvider.provideQuery();

        // Assert
        assertEquals(expectedQueryString, providedQuery, "Incorrect query string provided");
        verify(httpServletRequest, times(1)).getQueryString();
    }

    @Test
    public void testProvideQuery_WithNoQueryString() {
        // Arrange
        when(httpServletRequest.getQueryString()).thenReturn(null);

        // Act
        String providedQuery = currentResourceProvider.provideQuery();

        // Assert
        assertEquals("", providedQuery, "Incorrect query string provided");
        verify(httpServletRequest, times(1)).getQueryString();
    }
}
