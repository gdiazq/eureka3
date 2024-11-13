package com.iteureka.backeurekafour.mockito;

import com.iteureka.backeurekafour.CalculatorMath;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorMathTest {

    @Mock
    private CalculatorMath calculatorMath;

    @InjectMocks
    private CalculatorMathTest calculatorMathTest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAdd() {
        when(calculatorMath.add(10, 20)).thenReturn(30); 
        assertEquals(30, calculatorMath.add(10, 20)); 
        verify(calculatorMath, times(1));
        calculatorMath.add(10, 20);
    }

    @Test
    public void testSubtract() {
        when(calculatorMath.subtract(20, 10)).thenReturn(10);
        assertEquals(10, calculatorMath.subtract(20, 10));
        verify(calculatorMath, times(1));
        calculatorMath.subtract(20, 10);
    }

    @Test
    public void testMultiply() {
        when(calculatorMath.multiply(10, 20)).thenReturn(200);
        assertEquals(200, calculatorMath.multiply(10, 20));
        verify(calculatorMath, times(1));
        calculatorMath.multiply(10, 20);
    }

    @Test
    public void testDivide() {
        when(calculatorMath.divide(20, 10)).thenReturn(2);
        assertEquals(2, calculatorMath.divide(20, 10));
        verify(calculatorMath, times(1));
        calculatorMath.divide(20, 10);
    }

    @Test
    public void testModulo() {
        when(calculatorMath.modulo(20, 10)).thenReturn(0);
        assertEquals(0, calculatorMath.modulo(20, 10));
        verify(calculatorMath, times(1));
        calculatorMath.modulo(20, 10);
    }
}