package com.term1.calculator.controller;

import com.term1.calculator.dtos.request.DoMathRequestDto;
import com.term1.calculator.dtos.response.ApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MathControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testAddOperation() {
        DoMathRequestDto request = new DoMathRequestDto(1, 2, "+");
        ResponseEntity<ApiResponse> response = restTemplate.postForEntity("/api/calc/make_operation", request, ApiResponse.class);
        assertEquals(3, Objects.requireNonNull(Objects.requireNonNull(response.getBody())).getResult(), 0.01);
    }

    @Test
    public void testSubtractOperation() {
        DoMathRequestDto request = new DoMathRequestDto(1, 2, "-");
        ResponseEntity<ApiResponse> response = restTemplate.postForEntity("/api/calc/make_operation", request, ApiResponse.class);
        assertEquals(-1, Objects.requireNonNull(response.getBody()).getResult(), 0.01);
    }

    @Test
    public void testMultiplyOperation() {
        DoMathRequestDto request = new DoMathRequestDto(1, 2, "*");
        ResponseEntity<ApiResponse> response = restTemplate.postForEntity("/api/calc/make_operation", request, ApiResponse.class);
        assertEquals(2, Objects.requireNonNull(response.getBody()).getResult(), 0.01);
    }

    @Test
    public void testDivideOperation() {
        DoMathRequestDto request = new DoMathRequestDto(1, 2, "/");
        ResponseEntity<ApiResponse> response = restTemplate.postForEntity("/api/calc/make_operation", request, ApiResponse.class);
        assertEquals(0.5, Objects.requireNonNull(response.getBody()).getResult(), 0.01);
    }

    @Test
    public void testDivideByZero() {
        DoMathRequestDto request = new DoMathRequestDto(1, 0, "/");
        ResponseEntity<ApiResponse> response = restTemplate.postForEntity("/api/calc/make_operation", request, ApiResponse.class);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testInvalidOperation() {
        DoMathRequestDto request = new DoMathRequestDto(1, 2, "~");
        ResponseEntity<ApiResponse> response = restTemplate.postForEntity("/api/calc/make_operation", request, ApiResponse.class);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}