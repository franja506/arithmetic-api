package com.pachico.arithmetic.adapter.out.rest;

import com.pachico.arithmetic.adapter.out.rest.model.PercentageRequest;
import com.pachico.arithmetic.adapter.out.rest.model.PercentageResponse;
import com.pachico.arithmetic.application.port.out.RetrievePercentagePortOut;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

@Component
@Slf4j
public class PercentageClient implements RetrievePercentagePortOut {

    private final RestTemplate restTemplate;

    @Value("${franjagonca.rest.base-url}")
    private String base_url;

    @Autowired
    public PercentageClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @Retryable(backoff = @Backoff(delay = 1000), retryFor = RuntimeException.class)
    public BigDecimal execute(BigDecimal x, BigDecimal y) {
       BigDecimal result;
       try {
           val entity = new HttpEntity<>(createRequest(x,y),getHeaders(x,y));

           val percentage = restTemplate.postForObject(retrieveURL(),entity, PercentageResponse.class);

           result =  percentage.getPercentage();
       } catch (Exception e) {
           log.error("Hubo un error invocando la api percentage");
           throw new RuntimeException();
       }

       return result;
    }

    private String retrieveURL() {
        String PATH = "/percentage";
        return base_url + PATH;
    }

    private PercentageRequest createRequest(BigDecimal x, BigDecimal y) {
        return new PercentageRequest(x,y);
    }

    private MultiValueMap<String, String> getHeaders(BigDecimal x, BigDecimal y) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-mock-response-name", x.add(y).setScale(2, HALF_UP).toString());

        return headers;
    }


}
