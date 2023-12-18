package com.term1.calculator.services;

import com.term1.calculator.exceptions.InvalidOperationException;
import com.term1.calculator.services.impl.MathOperatorImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class MathOperatorIntegrationTest {


//    @Autowired
    private MathOperatorImpl mathOperator;

    @Test
    public void testDoMathAddition() throws InvalidOperationException {
        double result = mathOperator.doMath(10, 20, "+");
        assertEquals(30, result);
    }

    @Test
    public void testDoMathSubtraction() throws InvalidOperationException {
        double result = mathOperator.doMath(20, 10, "-");
        assertEquals(10, result);
    }

    @Test
    public void testDoMathMultiplication() throws InvalidOperationException {
        double result = mathOperator.doMath(10, 20, "*");
        assertEquals(200, result);
    }

    @Test
    public void testDoMathDivision() throws InvalidOperationException {
        double result = mathOperator.doMath(20, 10, "/");
        assertEquals(2, result);
    }

    @Test
    public void testDoMathDivisionByZero() {
        assertThrows(InvalidOperationException.class, () -> mathOperator.doMath(20, 0, "/"));
    }

    @Test
    public void testDoMathUnknownOperation() {
        assertThrows(RuntimeException.class, () -> mathOperator.doMath(20, 10, "^"));
    }

}

