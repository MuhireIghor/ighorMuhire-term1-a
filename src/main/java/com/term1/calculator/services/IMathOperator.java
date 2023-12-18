package com.term1.calculator.services;

import com.term1.calculator.exceptions.InvalidOperationException;

public interface IMathOperator {
    double doMath(double operand1, double operand2, String operation) throws InvalidOperationException;
}
