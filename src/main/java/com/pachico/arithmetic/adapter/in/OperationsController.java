package com.pachico.arithmetic.adapter.in;

import com.pachico.arithmetic.adapter.in.model.OperationRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class OperationsController {

    @PostMapping(path = "/operations")
    public BigInteger operations(@Validated @RequestBody OperationRequest operationRequest) {

        System.out.println("x:" + operationRequest.x);
         return operationRequest.x.add(operationRequest.y);
    }
}