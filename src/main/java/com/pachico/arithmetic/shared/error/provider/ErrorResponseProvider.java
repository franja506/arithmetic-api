package com.pachico.arithmetic.shared.error.provider;

import com.pachico.arithmetic.shared.error.model.ApiError;
import com.pachico.arithmetic.shared.error.model.ApiErrorResponse;
import com.pachico.arithmetic.shared.error.model.ApplicationErrors.ApplicationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
public class ErrorResponseProvider {

    private final CurrentResourceProvider currentResourceProvider;
    private final ErrorResponseMetadataProvider metadataProvider;
    @Autowired
    public ErrorResponseProvider(
            CurrentResourceProvider currentResourceProvider,
            ErrorResponseMetadataProvider metadataProvider
    ) {
        this.currentResourceProvider = currentResourceProvider;
        this.metadataProvider = metadataProvider;
    }

    public ApiErrorResponse provideFor(ApplicationError error) {
       ApiErrorResponse response = ApiErrorResponse
                .builder()
                .datetime(LocalDateTime.now().toString())
                .errors(List.of(
                        ApiError
                                .builder()
                                .resource(getResource())
                                .message(getDetail(error.getOrigin(), error.getMessage()))
                                .metadata(getMetadata())
                                .build()
                        )
        ).build();

       log.debug("error response provided {}", response);

       return response;
    }



    private String getResource() {
        return currentResourceProvider.provideUri();
    }

    private Map<String, String> getMetadata() {
        return metadataProvider.provide();
    }

    private String getDetail(Throwable error, String message) {
        Optional<String> throwableError = Optional.of(getOriginDetail(error));

        return throwableError.orElse(message);
    }

    private String getOriginDetail(Throwable error) {
        return error != null? error.getClass().getName()+":"+ error.getMessage():"";
    }
}
