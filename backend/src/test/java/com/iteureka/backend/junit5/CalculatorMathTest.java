package com.iteureka.backend.junit5;

import com.iteureka.backend.CalculatorMath;
import com.iteureka.backend.junit5.CalculatorMathTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorMathTest {

    public CalculatorMath calculatorMath;

    @BeforeEach 
    public void setUp() {
        calculatorMath = new CalculatorMath();
    }

    @Test
    public void testAdd() {
        assertEquals(30, calculatorMath.add(10, 20));
    }

    @Test
    public void testSubtract() {
        assertEquals(10, calculatorMath.subtract(20, 10));
    }

    @Test
    public void testMultiply() {
        assertEquals(200, calculatorMath.multiply(10, 20));
    }

    @Test
    public void testDivide() {
        assertEquals(2, calculatorMath.divide(20, 10));
    }

    @Test
    public void testModulo() {
        assertEquals(0, calculatorMath.modulo(20, 10));
    }

    @AfterEach
    public void tearDown() {
        calculatorMath = null;
    }

}
