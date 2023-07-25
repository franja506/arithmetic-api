package com.pachico.arithmetic.adapter.in.kafka;

import com.pachico.arithmetic.application.port.in.PersistOperationPortIn;
import com.pachico.arithmetic.domain.Operation;
import com.pachico.arithmetic.stubs.DomainObjectStubs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ActiveProfiles("test")
public class PersistOperationConsumerTest {

    @Mock
    private PersistOperationPortIn persistOperationPortIn;

    @Mock
    private Acknowledgment acknowledgment;

    @InjectMocks
    PersistOperationConsumer consumer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPersistOperationConsumer() {
        // Prepare the test data
        Operation operation = DomainObjectStubs.getOperation();

        // Mock the behavior of the persistOperationPortIn
        when(persistOperationPortIn.execute(operation)).thenReturn(operation);

        // Create a Kafka message with the test data
        consumer.execute(operation, acknowledgment);

        // Verify that the execute method was called with the correct operation
        verify(persistOperationPortIn).execute(operation);
        verify(acknowledgment).acknowledge();
    }
}
