package com.pachico.arithmetic.adapter.out.kafka;

import com.pachico.arithmetic.domain.Operation;
import com.pachico.arithmetic.stubs.DomainObjectStubs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PersistOperationProducerTest {

    @Mock
    private KafkaTemplate<String, Operation> mockKafkaTemplate;

    private PersistOperationProducer persistOperationProducer;

    @Captor
    private ArgumentCaptor<String> topicCaptor;

    @Captor
    private ArgumentCaptor<Operation> operationCaptor;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        String topic = "topic";
        persistOperationProducer = new PersistOperationProducer(mockKafkaTemplate, topic);
    }

    @Test
    public void givenOperation_WhenCalledProduceMethodInPersistOperationProducer_ThenSendToKafkaTopic() {

        // Given
        Operation operation = DomainObjectStubs.getOperation();

        // When
        persistOperationProducer.produce(operation);

        // Then
        verify(mockKafkaTemplate).send(topicCaptor.capture(), operationCaptor.capture());
        assertEquals("topic", topicCaptor.getValue());
        assertEquals(operation, operationCaptor.getValue());
    }
}
