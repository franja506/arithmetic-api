package com.pachico.arithmetic.application.port.in;

import com.pachico.arithmetic.domain.Operation;

public interface PersistOperationPortIn {
    Operation execute(Operation operation);
}
