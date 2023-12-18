package com.term1.calculator.services;

import com.term1.calculator.exceptions.InvalidOperationException;
import com.term1.calculator.services.impl.MathOperatorImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class MathOperatorImplTest {

    @InjectMocks
    private MathOperatorImpl mathOperator;

    @Test
    public void testDoMathWithMultiplication() throws InvalidOperationException {
        double result = mathOperator.doMath(3.0, 4.0, "*");
        assertEquals(12.0, result, 0.001);
    }

    @Test
    public void testDoMathWithDivision() throws InvalidOperationException {
        double result = mathOperator.doMath(6.0, 3.0, "/");
        assertEquals(2.0, result, 0.001);
    }

    @Test
    public void testDoMathAddition() throws InvalidOperationException {
        double result = mathOperator.doMath(4.0, 2.0, "+");
        assertEquals(6.0, result, 0.001);
    }

    @Test
    public void testDoMathWithSubtraction() throws InvalidOperationException {
        double result = mathOperator.doMath(7.0, 2.0, "-");
        assertEquals(5.0, result, 0.001);
    }

    @Test
    public void testDoMathWithDivisionByZero() {
        assertThrows(InvalidOperationException.class, () -> mathOperator.doMath(10.0, 0.0, "/"));
    }

    @Test
    public void testDoMathWithInvalidOperation() {
        assertThrows(RuntimeException.class, () -> mathOperator.doMath(10.0, 5.0, "%"));
    }
}