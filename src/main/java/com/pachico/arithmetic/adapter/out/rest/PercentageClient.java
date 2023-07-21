package com.pachico.arithmetic.adapter.out.rest;

import com.pachico.arithmetic.adapter.out.rest.model.PercentageRequest;
import com.pachico.arithmetic.adapter.out.rest.model.PercentageResponse;
import com.pachico.arithmetic.application.port.out.RetrievePercentagePortOut;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

@Component
public class PercentageClient implements RetrievePercentagePortOut {

    private final RestTemplate restTemplate;

    @Value("${franjagonca.rest.base-url}")
    private String base_url;

    private final String PATH = "/percentage";

    @Autowired
    public PercentageClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public BigDecimal execute(BigDecimal x, BigDecimal y) {

        val entity = new HttpEntity<>(createRequest(x,y),getHeaders(x,y));

        val percentage = restTemplate.postForObject(retrieveURL(),entity, PercentageResponse.class);

        return percentage.getPercentage();
    }

    private String retrieveURL() {
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
