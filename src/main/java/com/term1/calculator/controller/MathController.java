package com.term1.calculator.controller;



import com.term1.calculator.dtos.request.DoMathRequestDto;
import com.term1.calculator.dtos.response.ApiResponse;
import com.term1.calculator.exceptions.InvalidOperationException;
import com.term1.calculator.services.impl.MathOperatorImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/calc/make_operation")
public class MathController {
    private final MathOperatorImpl mathOperatorImpl;

    public MathController(MathOperatorImpl mathOperatorImpl) {
        this.mathOperatorImpl = mathOperatorImpl;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> doMath(@RequestBody DoMathRequestDto dto) throws InvalidOperationException {
        return ResponseEntity.ok(ApiResponse.success(mathOperatorImpl.doMath(dto.getOperand1(), dto.getOperand2(), dto.getOperation())));
    }
}