package com.nagpal.shivam.expressionparser;

import org.junit.Test;

import static com.nagpal.shivam.expressionparser.Helper.ABSOLUTE;
import static com.nagpal.shivam.expressionparser.Helper.evaluate;
import static org.junit.Assert.assertEquals;

public class LogarithmFunctions {
    @Test
    public void logNatural() throws Exception {
        String expression = "ln(e)";
        assertEquals(1.0, evaluate(expression), ABSOLUTE);

        expression = "ln(2)";
        assertEquals(0.6931471805599453, evaluate(expression), ABSOLUTE);

        expression = "ln(2.718281828459045)";
        assertEquals(1.0, evaluate(expression), ABSOLUTE);
    }

    @Test
    public void logBase10() throws Exception {
        String expression = "log(e)";
        assertEquals(0.4342944819032518, evaluate(expression), ABSOLUTE);

        expression = "log(10)";
        assertEquals(1.0, evaluate(expression), ABSOLUTE);

        expression = "log(2)";
        assertEquals(0.3010299956639812, evaluate(expression), ABSOLUTE);
    }

    @Test
    public void logBaseCustom() throws Exception {
        String expression = "log(16,4)";
        assertEquals(2.0, evaluate(expression), ABSOLUTE);

        expression = "log(e,e)";
        assertEquals(1.0, evaluate(expression), ABSOLUTE);

        expression = "log(10,10)";
        assertEquals(1.0, evaluate(expression), ABSOLUTE);
    }
}
