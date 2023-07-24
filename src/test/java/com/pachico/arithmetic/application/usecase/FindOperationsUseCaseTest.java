package com.pachico.arithmetic.application.usecase;

import static org.mockito.Mockito.*;

import com.pachico.arithmetic.application.port.out.FindOperationsPortOut;
import com.pachico.arithmetic.domain.Operation;
import com.pachico.arithmetic.domain.Pagination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;
// TODO: Terminar bien los tests de esta clase
public class FindOperationsUseCaseTest {

    private FindOperationsUseCase findOperationsUseCase;
    private FindOperationsPortOut operationService;

    @BeforeEach
    public void setup() {
        operationService = mock(FindOperationsPortOut.class);
        findOperationsUseCase = new FindOperationsUseCase(operationService);
    }

    @Test
    public void testExecute() {
        // Arrange
        Pagination pagination = new Pagination(1, 10); // Assuming you have a Pagination class
        Page<Operation> mockPage = createMockPage(); // Replace this with a method that creates a mock Page<Operation>

        // Mock the behavior of the operationService
        when(operationService.execute(any(Pagination.class))).thenReturn(mockPage);

        // Act
        Page<Operation> result = findOperationsUseCase.execute(pagination);

        // Assert
        verify(operationService, times(1)).execute(eq(pagination)); // Verify that the method was called with the correct argument
        // Add more assertions here based on your use case and the expected behavior of the method
    }

    // Replace this method with a method that creates a mock Page<Operation>
    private Page<Operation> createMockPage() {
        // Create and return a mock Page<Operation> using your preferred method/mock library
        return new PageImpl<>(Collections.emptyList());
    }
}
