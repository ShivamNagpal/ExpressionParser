package com.nagpal.shivam.expressionparser;

import org.junit.BeforeClass;
import org.junit.Test;

import static com.nagpal.shivam.expressionparser.Helper.*;
import static org.junit.Assert.assertEquals;

public class TrigonometricFunctionsDegreeNormalized {
    @BeforeClass
    public static void setupPreferences() {
        Expression.setAngleUnits(Expression.ANGLE_UNITS_DEGREE);
        Expression.setNormalizeTrigonometricFunctions(true);
    }

    @Test
    public void sine() throws Exception {
        String expression = "sin(90)";
        assertEquals(1.0, evaluate(expression), ABSOLUTE);

        expression = "sin(0)";
        assertEquals(0.0, evaluate(expression), ABSOLUTE);

        expression = "sin(30)";
        assertEquals(0.5, evaluate(expression), ABSOLUTE);

        // TODO: Check for supporting -pi directly
        expression = "sin(-90)";
        assertEquals(-1.0, evaluate(expression), ABSOLUTE);

        expression = "sin(180)";
        assertEquals(0.0, evaluate(expression), ABSOLUTE);

        expression = "sin(-30)";
        assertEquals(-0.5, evaluate(expression), ABSOLUTE);
    }

    @Test
    public void cosine() throws Exception {
        String expression = "cos(90)";
        assertEquals(0.0, evaluate(expression), ABSOLUTE);

        expression = "cos(0)";
        assertEquals(1.0, evaluate(expression), ABSOLUTE);

        expression = "cos(60)";
        assertEquals(0.5, evaluate(expression), ABSOLUTE);

        expression = "cos(-90)";
        assertEquals(0.0, evaluate(expression), ABSOLUTE);

        expression = "cos(180)";
        assertEquals(-1.0, evaluate(expression), ABSOLUTE);

        expression = "cos(-60)";
        assertEquals(0.5, evaluate(expression), ABSOLUTE);
    }

    @Test
    public void tangent() throws Exception {
        String expression = "tan(90)";
        assertEquals(Double.POSITIVE_INFINITY, evaluate(expression), DELTA);

        expression = "tan(0)";
        assertEquals(0.0, evaluate(expression), ABSOLUTE);

        expression = "tan(45)";
        assertEquals(1.0, evaluate(expression), DELTA);

        expression = "tan(-90)";
        assertEquals(Double.NEGATIVE_INFINITY, evaluate(expression), ABSOLUTE);

        expression = "tan(180)";
        assertEquals(0.0, evaluate(expression), DELTA);

        expression = "tan(-45)";
        assertEquals(-1.0, evaluate(expression), DELTA);
    }

    @Test
    public void antiSine() throws Exception {
        String expression = "asin(1)";
        assertEquals(90, evaluate(expression), ABSOLUTE);

        expression = "asin(-1)";
        assertEquals(-90, evaluate(expression), ABSOLUTE);

        expression = "asin(2)";
        checkForError(expression);

        expression = "asin(-2)";
        checkForError(expression);
    }

    @Test
    public void antiCosine() throws Exception {
        String expression = "acos(1)";
        assertEquals(0, evaluate(expression), ABSOLUTE);

        expression = "acos(-1)";
        assertEquals(180, evaluate(expression), ABSOLUTE);

        expression = "acos(2)";
        checkForError(expression);

        expression = "acos(-2)";
        checkForError(expression);
    }

    @Test
    public void antiTangent() throws Exception {
        String expression = "atan(1)";
        assertEquals(45, evaluate(expression), ABSOLUTE);

        expression = "atan(-1)";
        assertEquals(-45, evaluate(expression), ABSOLUTE);
    }

    @Test
    public void hyperbolicSine() throws Exception {
        String expression = "sinh(0)";
        assertEquals(0.0, evaluate(expression), ABSOLUTE);
    }

    @Test
    public void hyperbolicCosine() throws Exception {
        String expression = "cosh(0)";
        assertEquals(1.0, evaluate(expression), ABSOLUTE);
    }

    @Test
    public void hyperbolicTangent() throws Exception {
        String expression = "tanh(0)";
        assertEquals(0.0, evaluate(expression), ABSOLUTE);
    }
}
