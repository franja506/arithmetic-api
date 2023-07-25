package com.pachico.arithmetic.adapter.utils;

import com.pachico.arithmetic.application.port.out.OperationProducerPortOut;
import com.pachico.arithmetic.shared.utils.IdProvider;
import com.pachico.arithmetic.stubs.DomainObjectStubs;
import com.pachico.arithmetic.stubs.WebStubs;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccessLogFilterTest {

    @Mock
    private OperationProducerPortOut mockPersistOperation;

    @Mock
    private IdProvider mockIdProvider;

    @InjectMocks
    private AccessLogFilter accessLogFilter;

    @Test
    public void testDoFilterInternal() throws IOException, ServletException {
        // Mock the IdProvider
        when(mockIdProvider.provide()).thenReturn(DomainObjectStubs.uuid);

        // Create the mock HttpServletRequest and HttpServletResponse
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        mockRequest.setMethod(WebStubs.method);
        mockRequest.setRequestURI(WebStubs.uri);
        mockRequest.setContent(WebStubs.requestBody.getBytes());

        MockHttpServletResponse mockResponse = new MockHttpServletResponse();

        // Wrap the request and response with ContentCachingRequestWrapper and ContentCachingResponseWrapper
        ContentCachingRequestWrapper req = new ContentCachingRequestWrapper(mockRequest);
        ContentCachingResponseWrapper resp = new ContentCachingResponseWrapper(mockResponse);


        // Create a mock FilterChain
        FilterChain filterChain = mock(FilterChain.class);

        // Call the doFilterInternal method
        accessLogFilter.doFilterInternal(req, resp, filterChain);

        // Verify that the operation was produced with the expected data
        verify(mockPersistOperation).produce(argThat(operation -> operation.getId().equals(DomainObjectStubs.uuid)
                        && operation.getMethod().equals(WebStubs.method)
                        && operation.getUri().equals(WebStubs.uri)
        ));

        verify(filterChain).doFilter(any(), any());
    }
}

