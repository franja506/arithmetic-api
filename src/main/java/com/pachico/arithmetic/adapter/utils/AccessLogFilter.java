package com.pachico.arithmetic.adapter.utils;

import com.pachico.arithmetic.application.port.out.OperationProducerPortOut;
import com.pachico.arithmetic.domain.Operation;
import com.pachico.arithmetic.shared.utils.IdProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;

@Component
@Order(2)
@Slf4j
public class AccessLogFilter extends OncePerRequestFilter {

    private final OperationProducerPortOut persistOperation;
    private final IdProvider idProvider;

    @Autowired
    public AccessLogFilter(OperationProducerPortOut persistOperation, IdProvider idProvider) {
        this.persistOperation = persistOperation;
        this.idProvider = idProvider;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        ContentCachingRequestWrapper req = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper resp = new ContentCachingResponseWrapper(response);

        filterChain.doFilter(req, resp);

        // Get Cache
        String requestBody =  new String(req.getContentAsByteArray(), StandardCharsets.UTF_8);
        String responseBody = new String(resp.getContentAsByteArray(), StandardCharsets.UTF_8);
        String method = req.getMethod();
        String uri = req.getRequestURI();

        Operation operation = Operation.builder()
                                .id(idProvider.provide())
                                .method(method)
                                .uri(uri)
                                .request(requestBody)
                                .response(responseBody)
                                .datetime(OffsetDateTime.now())
                                .build();

        persistOperation.produce(operation);

        log.info("request body = {}", requestBody);

        log.info("response body = {}", responseBody);

        resp.copyBodyToResponse();
    }
}
