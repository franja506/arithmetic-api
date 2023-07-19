package com.pachico.arithmetic.adapter.out.kafka;

import com.pachico.arithmetic.application.port.out.OperationProducerPortOut;
import com.pachico.arithmetic.domain.Operation;
import org.springframework.stereotype.Component;

@Component
public class OperationProducer implements OperationProducerPortOut {
    @Override
    public void produce(Operation operation) {

    }
}
