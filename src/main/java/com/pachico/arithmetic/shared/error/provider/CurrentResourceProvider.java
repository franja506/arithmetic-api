package com.pachico.arithmetic.shared.error.provider;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
class CurrentResourceProvider {
    private HttpServletRequest httpServletRequest;
    @Autowired
    public CurrentResourceProvider(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public String provideUri() {
        String requestUri = httpServletRequest.getRequestURI();
        log.debug("uri provided: {}", requestUri);
        return requestUri;
    }

    public String provideQuery() {
        String queryString = Optional
                    .ofNullable(httpServletRequest.getQueryString())
                    .orElse("");

        log.debug("query string provided: {}", queryString);
        return queryString;
    }
}