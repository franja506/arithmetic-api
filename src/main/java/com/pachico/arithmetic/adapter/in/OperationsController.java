package com.pachico.arithmetic.adapter.in;

import com.pachico.arithmetic.adapter.in.model.OperationRequest;
import com.pachico.arithmetic.application.port.in.FindOperationsPortIn;
import com.pachico.arithmetic.application.port.in.GetSumPercentagePortIn;
import com.pachico.arithmetic.domain.Pagination;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.OK;

@RestController
@Slf4j
public class OperationsController {

    private final GetSumPercentagePortIn getSumPercentageService;
    private final FindOperationsPortIn operationsPortIn;

    @Autowired
    OperationsController(GetSumPercentagePortIn getSumPercentageService,
                         FindOperationsPortIn operationsPortIn) {
        this.getSumPercentageService = getSumPercentageService;
        this.operationsPortIn = operationsPortIn;
    }

    @PostMapping(path = "/calculate")
    @ResponseStatus(OK)
    @Operation(summary = "Operacion que suma dos digitos y le aplica el porcentaje de la suma de los mismos.")
    public BigDecimal calculate(@Valid @RequestBody OperationRequest request) {
        log.info("Estoy ingresando en el servicio /calculate con el request: {}", request);
        return getSumPercentageService.execute(request.x, request.y);
    }

    @GetMapping(path = "/operations")
    @ResponseStatus(OK)
    @Operation(summary = "Operacion que devuelve el historial de operaciones")
    public Page<com.pachico.arithmetic.domain.Operation> operations(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "50") Integer size
    ) {
        log.info("Estoy ingresando en el servicio ");
        return operationsPortIn.execute(new Pagination(page, size));
    }


}