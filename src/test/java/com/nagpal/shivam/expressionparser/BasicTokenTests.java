package com.nagpal.shivam.expressionparser;

import org.junit.Test;

import static com.nagpal.shivam.expressionparser.Helper.*;
import static org.junit.Assert.assertEquals;

public class BasicTokenTests {

    @Test
    public void combination() throws Exception {
        String expression = "5C2";
        assertEquals(10.0, evaluate(expression), ABSOLUTE);
    }

    @Test
    public void divide() throws Exception {
        String expression = "10/2";
        assertEquals(5.0, evaluate(expression), ABSOLUTE);

        expression = "5/3";
        assertEquals(1.6666666666666667, evaluate(expression), DELTA);
    }

    @Test
    public void dot() throws Exception {
        String expression = "5..2+5";
        checkForError(expression);

        expression = "5+.3";
        assertEquals(5.3, evaluate(expression), ABSOLUTE);
    }

    @Test
    public void exponentToken() throws Exception {
        String expression = "e/2";
        assertEquals(Math.E / 2, evaluate(expression), ABSOLUTE);
    }

    @Test
    public void exponentResult() throws Exception {
        String expression = "1E25";
        assertEquals(1E25, evaluate(expression), ABSOLUTE);
    }

    @Test
    public void factorial() throws Exception {
        String expression = "5!";
        assertEquals(120.0, evaluate(expression), ABSOLUTE);

        expression = "100!";
        assertEquals(9.332621544394418E157, evaluate(expression), ABSOLUTE);
    }

    @Test
    public void ignoreWhiteSpace() throws Exception {
        String expression = "5+  3";
        assertEquals(8.0, evaluate(expression), ABSOLUTE);

        expression = "5+  .3";
        assertEquals(5.3, evaluate(expression), ABSOLUTE);
    }

    @Test
    public void minusUnary() throws Exception {
        String expression = "-1";
        assertEquals(-1.0, evaluate(expression), ABSOLUTE);

        expression = "50+-30";
        assertEquals(20.0, evaluate(expression), ABSOLUTE);

        expression = "5--2";
        assertEquals(7, evaluate(expression), ABSOLUTE);
    }

    @Test
    public void minusBinary() throws Exception {
        String expression = "56-12";
        assertEquals(44, evaluate(expression), ABSOLUTE);
    }

    @Test
    public void mixedTokens() throws Exception {
        String expression = "9*10^3";
        assertEquals(9000.0, evaluate(expression), ABSOLUTE);

        //TODO: Try to match this result to absolute value
        expression = "3%*30";
        assertEquals(0.9, evaluate(expression), DELTA);

        expression = "3!*30";
        assertEquals(180.0, evaluate(expression), ABSOLUTE);
    }

    @Test
    public void mod() throws Exception {
        String expression = "5#2";
        assertEquals(1.0, evaluate(expression), ABSOLUTE);
    }

    @Test
    public void multiply() throws Exception {
        String expression = "5*2";
        assertEquals(10, evaluate(expression), ABSOLUTE);
    }

    @Test
    public void parenthesis() throws Exception {
        String expression = "(1)/2";
        assertEquals(0.5, evaluate(expression), ABSOLUTE);

        expression = "(-1)/2";
        assertEquals(-0.5, evaluate(expression), ABSOLUTE);
        expression = "((2 + 3) * 5)";
        assertEquals(25, evaluate(expression), ABSOLUTE);
    }

    @Test
    public void percent() throws Exception {
        String expression = "3%";
        assertEquals(0.03, evaluate(expression), ABSOLUTE);
    }

    @Test
    public void permutation() throws Exception {
        String expression = "5P2";
        assertEquals(20, evaluate(expression), ABSOLUTE);
    }

    @Test
    public void pi() throws Exception {
        String expression = "pi/2";
        assertEquals(Math.PI / 2, evaluate(expression), ABSOLUTE);
    }

    @Test
    public void plusUnary() throws Exception {
        String expression = "1";
        assertEquals(1.0, evaluate(expression), ABSOLUTE);

        expression = "50++30";
        assertEquals(80.0, evaluate(expression), ABSOLUTE);

        expression = "5-+2";
        assertEquals(3, evaluate(expression), ABSOLUTE);
    }

    @Test
    public void plusBinary() throws Exception {
        String expression = "3.2 + 6";
        assertEquals(9.2, evaluate(expression), ABSOLUTE);
    }

    @Test
    public void power() throws Exception {
        String expession = "2^5";
        assertEquals(32, evaluate(expession), ABSOLUTE);
    }
}
