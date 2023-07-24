package com.pachico.arithmetic.adapter.utils;

import com.pachico.arithmetic.application.port.in.PersistOperationPortIn;
import com.pachico.arithmetic.domain.Operation;
import com.pachico.arithmetic.shared.utils.IdProvider;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;

@Component
@Order(2)
@Slf4j
public class AccessLogFilter extends OncePerRequestFilter {

    private final PersistOperationPortIn persistOperationPortIn;
    private final IdProvider idProvider;

    @Autowired
    public AccessLogFilter(PersistOperationPortIn persistOperationPortIn, IdProvider idProvider) {
        this.persistOperationPortIn = persistOperationPortIn;
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

        persistOperationPortIn.execute(operation);

        log.info("request body = {}", requestBody);

        log.info("response body = {}", responseBody);

        // Finally remember to respond to the client with the cached data.
        resp.copyBodyToResponse();
    }
}
