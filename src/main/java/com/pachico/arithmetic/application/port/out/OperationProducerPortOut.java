package com.pachico.arithmetic.application.port.out;

import com.pachico.arithmetic.domain.Operation;

public interface OperationProducerPortOut {

    void produce(Operation operation);
}
