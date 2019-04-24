package com.nagpal.shivam.expressionparser;

import org.junit.Test;

import static com.nagpal.shivam.expressionparser.Helper.*;
import static org.junit.Assert.assertEquals;

public class SpecialCases {

    @Test
    public void ignoreWhiteSpace() throws Exception {
        String expression = "5+  3";
        assertEquals(8.0, evaluate(expression), ABSOLUTE);

        expression = "5+  .3";
        assertEquals(5.3, evaluate(expression), ABSOLUTE);

        expression = "   log ( e , e )   ";
        assertEquals(1.0, evaluate(expression), ABSOLUTE);
    }

    @Test
    public void autoParanthesisClosing() throws Exception {
        String expression = "(5+3*(2+2";
        assertEquals(17.0, evaluate(expression), ABSOLUTE);

        expression = "sqrt(4";
        assertEquals(2.0, evaluate(expression), ABSOLUTE);
    }

    @Test
    public void invalidTokens() throws Exception {
        String expression = "10cos(pi)";
        checkForError(expression);

        expression = "cos10(pi)";
        checkForError(expression);

        expression = "co10s(pi)";
        checkForError(expression);

        expression = "1tes(3)";
        checkForError(expression);

        expression = "cosp10(pi)";
        checkForError(expression);
    }
}
