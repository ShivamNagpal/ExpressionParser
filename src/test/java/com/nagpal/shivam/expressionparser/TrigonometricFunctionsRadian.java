package com.nagpal.shivam.expressionparser;

import org.junit.BeforeClass;
import org.junit.Test;

import static com.nagpal.shivam.expressionparser.Helper.*;
import static org.junit.Assert.assertEquals;

public class TrigonometricFunctionsRadian {

    @BeforeClass
    public static void setupPreferences() {
        Expression.setAngleUnits(Expression.ANGLE_UNITS_RADIAN);
        Expression.setNormalizeTrigonometricFunctions(false);
    }

    @Test
    public void sine() throws Exception {
        String expression = "sin(pi/2)";
        assertEquals(1.0, evaluate(expression), DELTA);

        expression = "sin(0)";
        assertEquals(0.0, evaluate(expression), DELTA);

        expression = "sin(pi/6)";
        assertEquals(0.5, evaluate(expression), DELTA);

        // TODO: Check for supporting -pi directly
        expression = "sin(-1*pi/2)";
        assertEquals(-1.0, evaluate(expression), DELTA);

        expression = "sin(pi)";
        assertEquals(0.0, evaluate(expression), DELTA);

        expression = "sin(-1*pi/6)";
        assertEquals(-0.5, evaluate(expression), DELTA);
    }

    @Test
    public void cosine() throws Exception {
        String expression = "cos(pi/2)";
        assertEquals(0.0, evaluate(expression), DELTA);

        expression = "cos(0)";
        assertEquals(1.0, evaluate(expression), DELTA);

        expression = "cos(pi/3)";
        assertEquals(0.5, evaluate(expression), DELTA);

        expression = "cos(-1*pi/2)";
        assertEquals(0.0, evaluate(expression), DELTA);

        expression = "cos(pi)";
        assertEquals(-1.0, evaluate(expression), DELTA);

        expression = "cos(-1*pi/3)";
        assertEquals(0.5, evaluate(expression), DELTA);
    }

    @Test
    public void tangent() throws Exception {
        String expression = "tan(pi/2)";
        assertEquals(1.633123935319537E16, evaluate(expression), ABSOLUTE);

        expression = "tan(0)";
        assertEquals(0.0, evaluate(expression), DELTA);

        expression = "tan(pi/4)";
        assertEquals(1.0, evaluate(expression), DELTA);

        expression = "tan(-1*pi/2)";
        assertEquals(-1.633123935319537E16, evaluate(expression), ABSOLUTE);

        expression = "tan(pi)";
        assertEquals(0.0, evaluate(expression), DELTA);

        expression = "tan(-1*pi/4)";
        assertEquals(-1.0, evaluate(expression), DELTA);
    }

    @Test
    public void antiSine() throws Exception {
        String expression = "asin(1)";
        assertEquals(Math.PI / 2, evaluate(expression), DELTA);

        expression = "asin(-1)";
        assertEquals(-Math.PI / 2, evaluate(expression), DELTA);

        expression = "asin(2)";
        checkForError(expression);

        expression = "asin(-2)";
        checkForError(expression);
    }

    @Test
    public void antiCosine() throws Exception {
        String expression = "acos(1)";
        assertEquals(0, evaluate(expression), DELTA);

        expression = "acos(-1)";
        assertEquals(Math.PI, evaluate(expression), DELTA);

        expression = "acos(2)";
        checkForError(expression);

        expression = "acos(-2)";
        checkForError(expression);
    }

    @Test
    public void antiTangent() throws Exception {
        String expression = "atan(1)";
        assertEquals(Math.PI / 4, evaluate(expression), DELTA);

        expression = "atan(-1)";
        assertEquals(-Math.PI / 4, evaluate(expression), DELTA);
    }

    @Test
    public void hyperbolicSine() throws Exception {
        String expression = "sinh(0)";
        assertEquals(0.0, evaluate(expression), DELTA);
    }

    @Test
    public void hyperbolicCosine() throws Exception {
        String expression = "cosh(0)";
        assertEquals(1.0, evaluate(expression), DELTA);
    }

    @Test
    public void hyperbolicTangent() throws Exception {
        String expression = "tanh(0)";
        assertEquals(0.0, evaluate(expression), DELTA);
    }
}
