package com.pdp.springm10;

import com.pdp.springm10.model.CustomMath;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomMathTest {

    private CustomMath customMath;

    @BeforeEach
    public void setUp() {
        customMath = new CustomMath();
    }

    @AfterEach
    void tearDown() {
        customMath = null;
    }

    @Test
    public void testSum_Success() {
        assertEquals(10, customMath.sum(1, 2, 3, 4));
    }

    @Test
    public void testSum_EmptyArguments() {
        assertEquals(0, customMath.sum());
    }

    @Test
    public void testSum_NullArguments() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> customMath.sum(null));
        assertEquals("Arguments can not be null", exception.getMessage());
    }

    @Test
    public void testSub_Success() {
        assertEquals(2, customMath.sub(5, 3));
    }

    @Test
    public void testAdd_Success() {
        assertEquals(7, customMath.add(3, 4));
    }

    @Test
    public void testDiv_Success() {
        assertEquals(2, customMath.div(6, 3));
    }

    @Test
    public void testDiv_DivisionByZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> customMath.div(5, 0));
        assertEquals("Divider can not be zero", exception.getMessage());
    }

    @Test
    public void testMul_Success() {
        assertEquals(15, customMath.mul(3, 5));
    }

    @Test
    public void testPow_Success() {
        assertEquals(27, customMath.pow(3, 3));
    }

    @Test
    public void testPow_ZeroPower() {
        assertEquals(1, customMath.pow(5, 0));
    }
}
