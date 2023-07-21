package com.pachico.arithmetic.application.port.out;

import com.pachico.arithmetic.domain.Operation;
import com.pachico.arithmetic.domain.Pagination;
import org.springframework.data.domain.Page;

public interface FindOperationsPortOut {
    Page<Operation> execute(Pagination pagination);
}
