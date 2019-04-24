package com.nagpal.shivam.expressionparser;

import org.junit.Test;

import static com.nagpal.shivam.expressionparser.Helper.ABSOLUTE;
import static com.nagpal.shivam.expressionparser.Helper.evaluate;
import static org.junit.Assert.assertEquals;

public class CalculusFunctions {

    @Test
    public void derivative() throws Exception {
        String expression = "der(X^2,2)";
        assertEquals(4.0, evaluate(expression), ABSOLUTE);

        expression = "der(X^3,2)";
        assertEquals(12.0, evaluate(expression), ABSOLUTE);

        expression = "der(X^4,2)";
        assertEquals(32.0, evaluate(expression), ABSOLUTE);

        expression = "der(X^23,2)";
        assertEquals(9.6468992005E7, evaluate(expression), ABSOLUTE);

        expression = "der(X^90,2)";
        assertEquals(5.5707301787585955E28, evaluate(expression), ABSOLUTE);
    }
}
