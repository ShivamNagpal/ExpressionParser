package com.nagpal.shivam.expressionparser;

class SupportMethods {

    static double factorial(int num) {
        double result = 1;
        while (num > 0) {
            result *= num;
            num--;
        }
        return result;
    }

    static double permutations(int n, int r) throws ExpressionParserException {
        if (n < r) {
            throw new ExpressionParserException(ExpressionParserException.MATH_ERROR);
        }
        return (factorial(n) / factorial((n - r)));
    }

    static double combinations(int n, int r) throws ExpressionParserException {
        if (n < r) {
            throw new ExpressionParserException(ExpressionParserException.MATH_ERROR);
        }
        return (factorial(n) / (factorial(r) * factorial(n - r)));
    }

    static boolean isInteger(double num) {
        return Double.isFinite(num) && (num == Math.floor(num));
    }
}
