package com.nagpal.shivam.expressionparser;

import org.junit.Test;

import static com.nagpal.shivam.expressionparser.Helper.*;
import static org.junit.Assert.assertEquals;

public class RootFunctions {

    @Test
    public void cubeRoot() throws Exception {
        String expression = "cbrt(8)";
        assertEquals(2.0, evaluate(expression), ABSOLUTE);

        expression = "cbrt(-8)";
        assertEquals(-2.0, evaluate(expression), ABSOLUTE);

        expression = "cbrt(-1)";
        assertEquals(-1.0, evaluate(expression), ABSOLUTE);
    }

    @Test
    public void squareRoot() throws Exception {
        String expression = "sqrt(25)";
        assertEquals(5.0, evaluate(expression), ABSOLUTE);

        expression = "sqrt(-1)";
        checkForError(expression);

        expression = "sqrt(2)";
        assertEquals(1.4142135623730951, evaluate(expression), ABSOLUTE);
    }
}
