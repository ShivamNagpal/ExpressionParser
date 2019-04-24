package com.nagpal.shivam.expressionparser;

class Helper {

    static final double ABSOLUTE = 0;
    static final double DELTA = 0.000000000000001;

    static double evaluate(String expression) throws Exception {
        return new Expression(expression).evaluate();
    }

    static void checkForError(String expression) {
        try {
            evaluate(expression);
            assert false;
        } catch (ExpressionParserException e) {
            assert true;
        } catch (Exception e) {
            assert false;
        }
    }
}
