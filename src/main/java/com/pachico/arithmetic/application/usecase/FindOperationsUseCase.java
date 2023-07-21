package com.pachico.arithmetic.application.usecase;

import com.pachico.arithmetic.application.port.in.FindOperationsPortIn;
import com.pachico.arithmetic.application.port.out.FindOperationsPortOut;
import com.pachico.arithmetic.domain.Operation;
import com.pachico.arithmetic.domain.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class FindOperationsUseCase implements FindOperationsPortIn {

    private final FindOperationsPortOut operationService;
    @Autowired
    public FindOperationsUseCase(FindOperationsPortOut operationService){ this.operationService = operationService; }
    @Override
    public Page<Operation> execute(Pagination pagination) {
        return operationService.execute(pagination);
    }
}
