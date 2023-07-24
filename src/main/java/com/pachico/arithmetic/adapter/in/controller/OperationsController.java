package com.pachico.arithmetic.adapter.in.controller;

import com.pachico.arithmetic.adapter.in.controller.mapper.ToOperationResponseMapper;
import com.pachico.arithmetic.adapter.in.controller.model.OperationRequest;
import com.pachico.arithmetic.adapter.in.controller.model.OperationResponse;
import com.pachico.arithmetic.application.port.in.FindOperationsPortIn;
import com.pachico.arithmetic.application.port.in.GetSumPercentagePortIn;
import com.pachico.arithmetic.domain.Pagination;
import com.pachico.arithmetic.shared.error.model.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.OK;

@RestController
@Slf4j
public class OperationsController {

    private final GetSumPercentagePortIn getSumPercentageService;
    private final FindOperationsPortIn operationsPortIn;

    private final ToOperationResponseMapper toOperationResponseMapper;

    @Autowired
    OperationsController(GetSumPercentagePortIn getSumPercentageService,
                         FindOperationsPortIn operationsPortIn,
                         ToOperationResponseMapper toOperationResponseMapper) {
        this.getSumPercentageService = getSumPercentageService;
        this.operationsPortIn = operationsPortIn;
        this.toOperationResponseMapper = toOperationResponseMapper;
    }

    @PostMapping(path = "/calculate", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    @Operation(
            summary = "Calculate a sum with a percentage",
            description = "Get a result of number1 and number2 and apply percentage to him"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = { @Content(schema = @Schema(implementation = ApiErrorResponse.class),
                                         mediaType = MediaType.APPLICATION_JSON_VALUE) }),
    })
    public OperationResponse calculate(@Valid @RequestBody OperationRequest request) {
        log.info("Estoy ingresando en el servicio /calculate con el request: {}", request);
        BigDecimal result = getSumPercentageService.execute(request.number1, request.number2);

        return toOperationResponseMapper.mapFrom(result);
    }

    @GetMapping(path = "/operations")
    @ResponseStatus(OK)
    @Operation(summary = "Operacion que devuelve el historial de operaciones")
    public Page<com.pachico.arithmetic.domain.Operation> operations(
            @RequestParam(required = false, defaultValue = "0")
            @PositiveOrZero Integer page,
            @RequestParam(required = false, defaultValue = "50")
            @PositiveOrZero Integer size
    ) {
        log.info("Estoy ingresando en el servicio /operations");
        return operationsPortIn.execute(new Pagination(page, size));
    }


}