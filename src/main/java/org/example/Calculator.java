package org.example;

public class Calculator {
    double addTwoNumbersReturnResult(double leftNumber, double rightNumber) {
        return leftNumber + rightNumber;
    }
    public static double divideTwoNumbersReturnResult(double leftNumber, double rightNumber) {
        if(rightNumber == 0.0) {
            throw new IllegalArgumentException(("division by zero is forbidden"));
        }
        return leftNumber / rightNumber;
    }
}
