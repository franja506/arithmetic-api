package com.pachico.arithmetic.shared.error.provider;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class ErrorResponseMetadataProvider {

    private final CurrentResourceProvider currentResourceProvider;
    @Autowired
    public ErrorResponseMetadataProvider(CurrentResourceProvider currentResourceProvider) {
        this.currentResourceProvider = currentResourceProvider;
    }
    public Map<String, String> provide() {
        Map<String, String> mapString = Map.of("query_string",currentResourceProvider.provideQuery());
        log.debug("metadata provided: {}", mapString);

        return mapString;
    }
}
