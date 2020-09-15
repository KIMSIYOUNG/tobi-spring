package com.example.tobibookspring.calculator;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalcSumTest {
    Calculator calculator;
    String path;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
        path = getClass().getResource("/numbers.txt").getPath();
    }

    @Test
    void sumOfNumbers() {
        int sum = calculator.calcSum(path);

        assertThat(sum).isEqualTo(10);
    }

    @Test
    void multiplyOfNumbers() {
        int sum = calculator.calcMultiply(path);

        assertThat(sum).isEqualTo(24);
    }
}