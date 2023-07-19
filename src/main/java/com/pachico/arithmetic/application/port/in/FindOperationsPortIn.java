package com.pachico.arithmetic.application.port.in;

import com.pachico.arithmetic.domain.Operation;
import com.pachico.arithmetic.domain.Pagination;
import org.springframework.data.domain.Page;

public interface FindOperationsPortIn {

    Page<Operation> execute(Pagination pagination);

}
