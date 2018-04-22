package com.nagpal.shivam.expressionparser;

class SupportMethods {

    static long factorial(int num) {
        long result = 1;
        while (num > 0) {
            result *= num;
            num--;
        }
        return result;
    }

    static long permutations(int n, int r) throws ExpressionParserException {
        if (n < r) {
            throw new ExpressionParserException(ExpressionParserException.MATH_ERROR);
        }
        return (factorial(n) / factorial((n - r)));
    }

    static long combinations(int n, int r) throws ExpressionParserException {
        if (n < r) {
            throw new ExpressionParserException(ExpressionParserException.MATH_ERROR);
        }
        return (factorial(n) / (factorial(r) * factorial(n - r)));
    }

    static boolean isInteger(double num) {
        return Double.isFinite(num) && (num == Math.floor(num));
    }
}
