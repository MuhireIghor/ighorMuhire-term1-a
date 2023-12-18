package com.term1.calculator.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.term1.calculator.dtos.request.DoMathRequestDto;
import com.term1.calculator.dtos.response.ApiResponse;
import com.term1.calculator.services.impl.MathOperatorImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class MathControllerEndToEndTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MathOperatorImpl mathOperator;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAdd() throws Exception {
        DoMathRequestDto request = new DoMathRequestDto(3.0, 4.0, "+");
//        ApiResponse response = new CalcResponse(7.0);

        when(mathOperator.doMath(3.0, 4.0, "+")).thenReturn(7.0);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/calc/make_operation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.result").value(7.0));
    }

    @Test
    void testSubtract() throws Exception {
        DoMathRequestDto request = new DoMathRequestDto(3.0, 4.0, "-");
//        CalcResponse response = new CalcResponse(-1.0);

        when(mathOperator.doMath(3.0, 4.0, "-")).thenReturn(-1.0);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/calc/make_operation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.result").value(-1.0));
    }

    @Test
    void testMultiplyAction() throws Exception {
        DoMathRequestDto request = new DoMathRequestDto(3.0, 7.0, "*");


        when(mathOperator.doMath(3.0, 7.0, "*")).thenReturn(21.0);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/calc/make_operation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.result").value(21.0));
    }

    @Test
    void testDivide() throws Exception {
        DoMathRequestDto request = new DoMathRequestDto(4.0, 2.0, "/");
//        CalcResponse response = new CalcResponse(0.75);

        when(mathOperator.doMath(4.0, 2.0, "/")).thenReturn(2.0);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/calc/make_operation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.result").value(0.75));
    }

    @Test
    void testInvalidOperationEndpoint() throws Exception {
        DoMathRequestDto request = new DoMathRequestDto(3.0, 4.0, "~");

        when(mathOperator.doMath(3.0, 4.0, "~")).thenThrow(new RuntimeException("Invalid operator please try again!"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/calc/make_operation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }
    @Test
    void testDivideByZeroEndpoint() throws Exception {
        DoMathRequestDto request = new DoMathRequestDto(3.0, 0.0, "/");

        when(mathOperator.doMath(3.0, 0.0, "/")).thenThrow(new RuntimeException("No zero divider!"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/calc/make_operation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }

}