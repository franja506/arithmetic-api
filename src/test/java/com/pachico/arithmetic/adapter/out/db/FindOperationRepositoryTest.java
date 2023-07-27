package com.pachico.arithmetic.adapter.out.db;

import com.pachico.arithmetic.domain.Operation;
import com.pachico.arithmetic.domain.Pagination;
import com.pachico.arithmetic.stubs.DomainObjectStubs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class FindOperationRepositoryTest {

    @Autowired
    private FindOperationRepository findOperationRepository;

    @Autowired
    private DbRepository persistor;

    @BeforeEach
    public void populationDatabase() {
        persistor.saveAll(DomainObjectStubs.getOperations());
    }

    @Test
    public void givenPagination_whenCallToExecute_thenReturnSuccessfulPageOperation() {
        // Given
        Pagination pagination = new Pagination(0, 10);

        // When
        Page<Operation> resultPage = findOperationRepository.execute(pagination);

        // Then
        assertNotNull(resultPage);
        assertEquals(4, resultPage.getNumberOfElements());
    }
}