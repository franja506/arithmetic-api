package com.pachico.arithmetic.adapter.in;

import com.pachico.arithmetic.adapter.in.model.OperationRequest;
import com.pachico.arithmetic.application.port.in.GetSumPercentagePortIn;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.OK;

@RestController
@Slf4j
public class OperationsController {

    private final GetSumPercentagePortIn getSumPercentageService;

    @Autowired
    OperationsController(GetSumPercentagePortIn getSumPercentageService) {
        this.getSumPercentageService = getSumPercentageService;
    }

    @PostMapping(path = "/operations")
    @ResponseStatus(OK)
    @Operation(summary = "Operacion de suma de dos digitos ")
    public BigDecimal operations(@RequestBody OperationRequest request) {
        log.info("Estoy ingresando en el servicio ");
        return getSumPercentageService.execute(request.x, request.y);
    }
}