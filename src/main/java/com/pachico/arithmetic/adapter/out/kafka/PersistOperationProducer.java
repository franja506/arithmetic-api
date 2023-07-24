package com.pachico.arithmetic.adapter.out.kafka;

import com.pachico.arithmetic.application.port.out.OperationProducerPortOut;
import com.pachico.arithmetic.domain.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PersistOperationProducer implements OperationProducerPortOut {

    private final KafkaTemplate<String, Operation> kafkaTemplate;
    @Value("${event.topic.operation.created}")
    private String topic;

    @Autowired
    public PersistOperationProducer(KafkaTemplate<String, Operation> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void produce(Operation operation) {
        try {
            kafkaTemplate.send(topic, operation);
            log.info("created operation event produced: {}", operation);

        } catch (Exception e) {

            log.error("Error with produce createdPayment event: {}", operation);
        }
    }
}
