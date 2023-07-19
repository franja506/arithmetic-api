package com.pachico.arithmetic.application.usecase;

import com.pachico.arithmetic.application.port.in.FindOperationsPortIn;
import com.pachico.arithmetic.domain.Operation;
import com.pachico.arithmetic.domain.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class FindOperationsUseCase implements FindOperationsPortIn {
    @Override
    public Page<Operation> execute(Pagination pagination) {
        return null;
    }
}
