package com.term1.calculator.controller;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.term1.calculator.dtos.request.DoMathRequestDto;
import com.term1.calculator.dtos.response.ApiResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MathControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void doMathOperation_Success() {
        DoMathRequestDto dto = new DoMathRequestDto(2, 5, "+");

        ResponseEntity<ApiResponse> response = this.restTemplate.postForEntity("/api/calc/make_operation", dto, ApiResponse.class);

        assertEquals(200, response.getStatusCode().value());
    }
}