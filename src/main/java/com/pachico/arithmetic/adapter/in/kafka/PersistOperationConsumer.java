package com.pachico.arithmetic.adapter.in.kafka;

import com.pachico.arithmetic.application.port.in.PersistOperationPortIn;
import com.pachico.arithmetic.domain.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PersistOperationConsumer {

    private final PersistOperationPortIn persistOperationPortIn;

    @Autowired
    public PersistOperationConsumer(PersistOperationPortIn persistOperationPortIn) {
        this.persistOperationPortIn = persistOperationPortIn;
    }

    @KafkaListener(
            topics = "${event.topic.operation.created}",
            groupId = "${event.group.persist.operation}"
    )
    @RetryableTopic(
            retryTopicSuffix = ".persist-retry", dltTopicSuffix = ".persist-dlt"
    )
    public Operation execute(@Payload Operation operation, Acknowledgment ack) {
        Operation operation1 =  persistOperationPortIn.execute(operation);
        ack.acknowledge();

        return operation1;
    }
}
