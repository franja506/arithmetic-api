package com.pachico.arithmetic.adapter.in.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pachico.arithmetic.adapter.in.controller.mapper.ToOperationResponseMapper;
import com.pachico.arithmetic.adapter.in.controller.model.OperationRequest;
import com.pachico.arithmetic.adapter.in.controller.model.OperationResponse;
import com.pachico.arithmetic.application.port.in.FindOperationsPortIn;
import com.pachico.arithmetic.application.port.in.GetSumPercentagePortIn;
import com.pachico.arithmetic.domain.Operation;
import com.pachico.arithmetic.domain.Pagination;
import com.pachico.arithmetic.shared.error.GlobalExceptionHandler;
import com.pachico.arithmetic.shared.error.model.PercentageApiNotAvailableException;
import com.pachico.arithmetic.stubs.DomainObjectStubs;
import com.pachico.arithmetic.stubs.SharedStubs;
import com.pachico.arithmetic.stubs.WebStubs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(OperationsController.class)
@ContextConfiguration(classes = {OperationsController.class, GlobalExceptionHandler.class})
public class OperationsControllerTest {

    @MockBean
    private GetSumPercentagePortIn getSumPercentageService;

    @MockBean
    private FindOperationsPortIn operationsPortIn;

    @MockBean
    private ToOperationResponseMapper toOperationResponseMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenCorrectRequest_whenInvokeToCalculatesPath_thenReturnOperationResponse() throws Exception {
        // Given
        OperationRequest request = WebStubs.getOperationRequest();
        BigDecimal expectedPercentage = SharedStubs.percentage;
        OperationResponse expectedResult = WebStubs.getOperationResponse();
        when(getSumPercentageService.execute(any(BigDecimal.class), any(BigDecimal.class))).thenReturn(expectedPercentage);
        when(toOperationResponseMapper.mapFrom(any(BigDecimal.class))).thenReturn(WebStubs.getOperationResponse());

        // When and Then
        mockMvc.perform(MockMvcRequestBuilders.post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expectedResult)));
    }

    @Test
    public void givenCorrectRequest_whenInvokeToCalculatesPath_thenReturnErrorByPercentageApiNotRespond() throws Exception {
        // Given
        OperationRequest request = WebStubs.getOperationRequest();
        ProblemDetail expectedResponse = WebStubs.getPercentageApiNotExceptionResponse();
        when(getSumPercentageService.execute(any(BigDecimal.class), any(BigDecimal.class))).thenThrow(PercentageApiNotAvailableException.class);

        // When and Then
        mockMvc.perform(MockMvcRequestBuilders.post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().is(500))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expectedResponse)));
    }

    @Test
    public void givenCorrectRequest_whenInvokeToCalculatesPath_thenReturnErrorByException() throws Exception {
        // Given
        OperationRequest request = WebStubs.getOperationRequest();
        ProblemDetail expectedResponse = WebStubs.getInternalErrorResponse();
        when(getSumPercentageService.execute(any(BigDecimal.class), any(BigDecimal.class))).thenThrow(new RuntimeException("Error message"));

        // When and Then
        mockMvc.perform(MockMvcRequestBuilders.post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().is(500))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expectedResponse)));
    }

    @Test
    public void givenEmptyRequest_whenInvokeToCalculatesPath_thenReturnErrorByHttpMessageNotReadableException() throws Exception {
        // Given
        ProblemDetail expectedResponse = WebStubs.getHttpMessageNotReadableResponse();

        // When and Then
        mockMvc.perform(MockMvcRequestBuilders.post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expectedResponse)));
    }

    @Test
    public void givenBadRequest_whenInvokeToCalculatesPath_thenReturnErrorByMethodArgumentNotValidException() throws Exception {
        // Given
        OperationRequest request = WebStubs.getOperationRequest();
        request.setNumber1(null);
        ProblemDetail expectedResponse = WebStubs.getHttpMessageNotReadableResponse();

        // When and Then
        mockMvc.perform(MockMvcRequestBuilders.post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expectedResponse)));
    }

    @Test
    public void givenCorrectRequest_whenInvokeToOperationsPath_thenReturnOperationsResponse() throws Exception {
        // Given
        int page = 0;
        int size = 50;
        Page<Operation> expectedResult = new PageImpl<>(DomainObjectStubs.getOperations(), PageRequest.of(page, size), 1) {
        };
        when(operationsPortIn.execute(any(Pagination.class))).thenReturn(expectedResult);

        // When and Then
        mockMvc.perform(MockMvcRequestBuilders.get("/operations")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expectedResult)));
    }
}
