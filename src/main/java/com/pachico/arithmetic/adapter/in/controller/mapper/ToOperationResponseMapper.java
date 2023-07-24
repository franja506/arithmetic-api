package com.pachico.arithmetic.adapter.in.controller.mapper;

import com.pachico.arithmetic.adapter.in.controller.model.OperationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
public class ToOperationResponseMapper {
    public OperationResponse mapFrom(BigDecimal result) {
       return new OperationResponse(result);
    }
}
