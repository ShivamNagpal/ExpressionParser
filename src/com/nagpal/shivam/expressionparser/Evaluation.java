/*
 * Copyright 2018 Shivam Nagpal
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nagpal.shivam.expressionparser;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Stack;

class Evaluation {
    private static DecimalFormat decimalFormat = new DecimalFormat("#0.000000000000000");

    static ArrayList<TokenNode> tokenizeInfix(String expression) throws ExpressionParserException {
        StringBuilder expressionStringBuilder = new StringBuilder(expression);
        expressionStringBuilder.append('\0');
        ArrayList<TokenNode> infixTokenNodeArrayList = new ArrayList<>();
        int length = expressionStringBuilder.length();
        int previousIndex = 0;
        OperandFlags operandFlags = new OperandFlags();
        char ch;

        for (int i = 0; i < length; i++) {
            ch = expressionStringBuilder.charAt(i);
            while (Token.isSpace(ch)) {
                expressionStringBuilder.deleteCharAt(i);
                length -= 1;
                ch = expressionStringBuilder.charAt(i);
            }

            if (Token.isLiteralOperand(ch)) {
                if (!operandFlags.derivedOperandFlag) {
                    operandFlags.literalOperandFlag = true;
                }
            } else if (Token.isDecimal(ch)) {
                if (operandFlags.decimalFlag) {
                    throw new ExpressionParserException(ExpressionParserException.INVALID_OPERAND);
                }
                operandFlags.decimalFlag = true;
            } else if (Token.isDerivedOperand(ch)) {
                if (operandFlags.literalOperandFlag) {
                    throw new ExpressionParserException(ExpressionParserException.INSUFFICIENT_OPERATORS);
                }
                operandFlags.derivedOperandFlag = true;
            } else {
                if (operandFlags.literalOperandFlag) {
                    operandFlags.resetFlags();

                    infixTokenNodeArrayList.add(new TokenNode(TokenNode.TYPE_OPERAND, TokenNode.SUB_TYPE_OPERAND_LITERAL, expressionStringBuilder.substring(previousIndex, i)));
                    previousIndex = i + 1;
                } else if (operandFlags.derivedOperandFlag) {
                    operandFlags.resetFlags();
                    if (ch == '(') {
                        int openBraces = 0;
                        while (i < length) {
                            char tempCh = expressionStringBuilder.charAt(i);
                            if (tempCh == '(') {
                                openBraces += 1;
                            } else if (tempCh == ')') {
                                openBraces -= 1;
                                if (openBraces == 0) {
                                    break;
                                }
                            }
                            i++;
                        }
                        infixTokenNodeArrayList.add(new TokenNode(TokenNode.TYPE_OPERAND, TokenNode.SUB_TYPE_OPERAND_FUNCTION, expressionStringBuilder.substring(previousIndex, i + 1)));
                        continue;
                    } else {
                        infixTokenNodeArrayList.add(new TokenNode(TokenNode.TYPE_OPERAND, TokenNode.SUB_TYPE_OPERAND_SYMBOL, expressionStringBuilder.substring(previousIndex, i)));
                    }
                    previousIndex = i + 1;

                }

                if (Token.isParenthesis(ch)) {
                    infixTokenNodeArrayList.add(new TokenNode(TokenNode.TYPE_PARENTHESIS, Token.currentTokenSubType, Character.toString(ch)));
                    previousIndex = i + 1;

                } else if (Token.isOperator(ch)) {
                    int lastIndex = infixTokenNodeArrayList.size() - 1;
                    if (!infixTokenNodeArrayList.isEmpty() && (infixTokenNodeArrayList.get(lastIndex).getType() == TokenNode.TYPE_OPERAND || infixTokenNodeArrayList.get(lastIndex).getSubType() == TokenNode.SUB_TYPE_OPERATOR_UNARY || infixTokenNodeArrayList.get(lastIndex).getSubType() == TokenNode.SUB_TYPE_PARENTHESIS_RIGHT)) {
                        infixTokenNodeArrayList.add(new TokenNode(TokenNode.TYPE_OPERATOR, Token.currentTokenSubType, Character.toString(ch)));
                        previousIndex = i + 1;

                    } else {
                        if (ch == '+' || ch == '-') {
                            if (operandFlags.signFlag) {
                                throw new ExpressionParserException(ExpressionParserException.INVALID_EXPRESSION);
                            }
                            operandFlags.signFlag = true;
                        } else {
                            throw new ExpressionParserException(ExpressionParserException.INVALID_EXPRESSION);

                        }
                    }
                } else {
                    if (ch != '\u0000') {
                        throw new ExpressionParserException(ExpressionParserException.INVALID_TOKEN);
                    }
                }
            }
        }
        return infixTokenNodeArrayList;
    }

    static ArrayList<TokenNode> infixToPostfix(ArrayList<TokenNode> infixTokenNodeArrayList) {
        ArrayList<TokenNode> postfixTokenNodeArrayList = new ArrayList<>();
        Stack<TokenNode> tokenNodeStack = new Stack<>();
        for (TokenNode node : infixTokenNodeArrayList) {
            switch (node.getType()) {
                case TokenNode.TYPE_OPERAND:
                    postfixTokenNodeArrayList.add(node);
                    break;

                case TokenNode.TYPE_PARENTHESIS:
                    if (node.getToken().contentEquals(")")) {
                        while (!tokenNodeStack.peek().getToken().contentEquals("(")) {
                            postfixTokenNodeArrayList.add(tokenNodeStack.pop());
                        }
                        tokenNodeStack.pop();
                    } else {
                        tokenNodeStack.push(node);
                    }

                    break;
                case TokenNode.TYPE_OPERATOR:

                    while (!tokenNodeStack.isEmpty() && Token.precedence(tokenNodeStack.peek().getToken()) >= Token.precedence(node.getToken())) {
                        postfixTokenNodeArrayList.add(tokenNodeStack.pop());
                    }
                    tokenNodeStack.push(node);

                    break;
            }

        }

        while (!tokenNodeStack.isEmpty()) {
            TokenNode node = tokenNodeStack.pop();
            if (node.getType() != TokenNode.TYPE_PARENTHESIS) {
                postfixTokenNodeArrayList.add(node);
            }
        }
        return postfixTokenNodeArrayList;
    }

    static double postfixEvaluation(ArrayList<TokenNode> postfixTokenNodeArrayList) throws ExpressionParserException {
        Stack<TokenNode> tokenNodeStack = new Stack<>();
        for (TokenNode node : postfixTokenNodeArrayList) {
            if (node.getType() == TokenNode.TYPE_OPERAND) {
                switch (node.getSubType()) {
                    case TokenNode.SUB_TYPE_OPERAND_LITERAL:
                        tokenNodeStack.push(node);
                        break;

                    case TokenNode.SUB_TYPE_OPERAND_SYMBOL:
                        tokenNodeStack.push(substituteSymbolOperands(node.getToken()));
                        break;
                    case TokenNode.SUB_TYPE_OPERAND_FUNCTION:
                        tokenNodeStack.push(evaluateFunctionExpression(node.getToken()));
                        break;
                }
            } else if (node.getType() == TokenNode.TYPE_OPERATOR) {
                switch (node.getSubType()) {
                    case TokenNode.SUB_TYPE_OPERATOR_UNARY:
                        if (tokenNodeStack.size() >= 1) {
                            TokenNode opd = tokenNodeStack.pop();
                            tokenNodeStack.push(new TokenNode(TokenNode.TYPE_OPERAND, TokenNode.SUB_TYPE_OPERAND_LITERAL, Double.toString(performOperation(opd.getToken(), node.getToken()))));
                        } else {
                            throw new ExpressionParserException(ExpressionParserException.INVALID_EXPRESSION);
                        }
                        break;
                    case TokenNode.SUB_TYPE_OPERATOR_BINARY:
                        if (tokenNodeStack.size() >= 2) {
                            TokenNode opd2 = tokenNodeStack.pop();
                            TokenNode opd1 = tokenNodeStack.pop();
                            tokenNodeStack.push(new TokenNode(TokenNode.TYPE_OPERAND, TokenNode.SUB_TYPE_OPERAND_LITERAL, Double.toString(performOperation(opd1.getToken(), opd2.getToken(), node.getToken()))));
                        } else {
                            throw new ExpressionParserException(ExpressionParserException.INVALID_EXPRESSION);
                        }
                        break;
                }

            }
        }
        if (tokenNodeStack.isEmpty() || tokenNodeStack.size() != 1) {
            throw new ExpressionParserException(ExpressionParserException.INVALID_EXPRESSION);
        }
        return Double.parseDouble(tokenNodeStack.peek().getToken());
    }

    private static double performOperation(String opdStr, String oprStr) throws ExpressionParserException {
        double opd = Double.parseDouble(opdStr);
        switch (oprStr) {
            case "!":
                double result = 1;
                while (opd > 0) {
                    result *= opd;
                    opd--;
                }
                return result;
            case "%":
                return opd / 100;
        }
        throw new ExpressionParserException(ExpressionParserException.INVALID_UNARY_OPERATOR);
    }

    private static double performOperation(String opdStr1, String opdStr2, String oprStr) throws ExpressionParserException {
        double op1 = Double.parseDouble(opdStr1);
        double op2 = Double.parseDouble(opdStr2);
        switch (oprStr) {
            case "+":
                return op1 + op2;
            case "-":
                return op1 - op2;
            case "*":
                return op1 * op2;
            case "/":
                return op1 / op2;
            case "#":
                return op1 % op2;
            case "^":
                return Math.pow(op1, op2);
            case "E":
                return op1 * Math.pow(10, op2);
        }

        throw new ExpressionParserException(ExpressionParserException.INVALID_BINARY_OPERATOR);

    }

    private static TokenNode evaluateFunctionExpression(String nodeStr) throws ExpressionParserException {
        int expressionIndex = nodeStr.indexOf('(');
        String expressionStr = nodeStr.substring(expressionIndex + 1, nodeStr.length() - 1);
        String functionStr = nodeStr.substring(0, expressionIndex);
        double result = Double.NaN;

        switch (functionStr.length()) {

            case 2:
                switch (functionStr) {
                    case "ln": {
                        Expression expression = new Expression(expressionStr);
                        result = expression.evaluate();
                        result = Math.log(result);
                        break;
                    }
                    default:
                        throw new ExpressionParserException(ExpressionParserException.INVALID_FUNCTION);
                }
                break;
            case 3:
                switch (functionStr) {
                    case "sin": {
                        Expression expression = new Expression(expressionStr);
                        result = expression.evaluate();
                        if (Expression.angleUnits == Expression.ANGLE_UNITS_DEGREE) {
                            result = Math.toRadians(result);
                        }
                        result = Math.sin(result);
                        if (Expression.normalizeTrigonometricFunctions) {
                            result = Double.parseDouble(decimalFormat.format(result));
                        }
                        break;
                    }
                    case "cos": {
                        Expression expression = new Expression(expressionStr);
                        result = expression.evaluate();
                        if (Expression.angleUnits == Expression.ANGLE_UNITS_DEGREE) {
                            result = Math.toRadians(result);
                        }
                        result = Math.cos(result);
                        if (Expression.normalizeTrigonometricFunctions) {
                            result = Double.parseDouble(decimalFormat.format(result));
                        }
                        break;
                    }
                    case "tan": {
                        Expression expression = new Expression(expressionStr);
                        result = expression.evaluate();
                        if (Expression.angleUnits == Expression.ANGLE_UNITS_DEGREE) {
                            result = Math.toRadians(result);
                        }
                        result = Math.tan(result);
                        if (Expression.normalizeTrigonometricFunctions) {
                            if (result > 1E15) {
                                result = Double.POSITIVE_INFINITY;
                            } else if (result < -1E15) {
                                result = Double.NEGATIVE_INFINITY;
                            }
                        }
                        break;
                    }
                    case "log": {
                        Expression expression = new Expression(expressionStr);
                        result = expression.evaluate();
                        result = Math.log10(result);
                        break;
                    }
                    case "der": {
                        int i = expressionStr.indexOf(',');
                        String function = expressionStr.substring(0, i);
                        String value = expressionStr.substring(i + 1);
                        StringBuilder derivate = new StringBuilder();
                        String h = "0.000001";
                        derivate.append(function.replaceAll("X", "(" + value + "+" + h + ")")).append("-").append(function.replaceAll("X", "(" + value + "-" + h + ")"));
                        derivate.insert(0, '(').append(")/(2*").append(h).append(")");
                        Expression expression = new Expression(derivate.toString());
                        result = expression.evaluate();
                        result = Double.parseDouble(String.format("%.3f", result));
                        break;
                    }
                    default:
                        throw new ExpressionParserException(ExpressionParserException.INVALID_FUNCTION);
                }
                break;
            case 4:
                switch (functionStr) {
                    case "sqrt": {
                        Expression expression = new Expression(expressionStr);
                        result = expression.evaluate();
                        result = Math.sqrt(result);
                        break;
                    }
                    case "cbrt": {
                        Expression expression = new Expression(expressionStr);
                        result = expression.evaluate();
                        result = Math.cbrt(result);
                        break;
                    }
                    case "asin": {
                        Expression expression = new Expression(expressionStr);
                        result = expression.evaluate();
                        result = Math.asin(result);
                        if (Expression.angleUnits == Expression.ANGLE_UNITS_DEGREE) {
                            result = Math.toDegrees(result);
                        }
                        break;
                    }
                    case "acos": {
                        Expression expression = new Expression(expressionStr);
                        result = expression.evaluate();
                        result = Math.acos(result);
                        if (Expression.angleUnits == Expression.ANGLE_UNITS_DEGREE) {
                            result = Math.toDegrees(result);
                        }
                        break;
                    }
                    case "atan": {
                        Expression expression = new Expression(expressionStr);
                        result = expression.evaluate();
                        result = Math.atan(result);
                        if (Expression.angleUnits == Expression.ANGLE_UNITS_DEGREE) {
                            result = Math.toDegrees(result);
                        }
                        break;
                    }
                    case "sinh": {
                        Expression expression = new Expression(expressionStr);
                        result = expression.evaluate();
                        result = Math.sinh(result);
                        break;
                    }
                    case "cosh": {
                        Expression expression = new Expression(expressionStr);
                        result = expression.evaluate();
                        result = Math.cosh(result);
                        break;
                    }
                    case "tanh": {
                        Expression expression = new Expression(expressionStr);
                        result = expression.evaluate();
                        result = Math.tanh(result);
                        break;
                    }
                    default:
                        throw new ExpressionParserException(ExpressionParserException.INVALID_FUNCTION);
                }
                break;
            default:
                throw new ExpressionParserException(ExpressionParserException.INVALID_FUNCTION);
        }

        if (Double.isNaN(result)) {
            throw new ExpressionParserException(ExpressionParserException.MATH_ERROR);
        }

        return new TokenNode(TokenNode.TYPE_OPERAND, TokenNode.SUB_TYPE_OPERAND_LITERAL, Double.toString(result));
    }

    private static TokenNode substituteSymbolOperands(String node) throws ExpressionParserException {
        double result = Double.NaN;
        switch (node.length()) {
            case 1:
                switch (node) {
                    case "e":
                        result = Math.E;
                }
                break;
            case 2:
                switch (node) {
                    case "pi":
                        result = Math.PI;
                }
                break;
        }
        if (Double.isNaN(result)) {
            throw new ExpressionParserException(ExpressionParserException.INVALID_SYMBOL);
        }
        return new TokenNode(TokenNode.TYPE_OPERAND, TokenNode.SUB_TYPE_OPERAND_LITERAL, Double.toString(result));
    }


}

class OperandFlags {
    boolean derivedOperandFlag = false;
    boolean literalOperandFlag = false;
    boolean decimalFlag = false;
    boolean signFlag = false;

    void resetFlags() {
        derivedOperandFlag = false;
        literalOperandFlag = false;
        decimalFlag = false;
        signFlag = false;
    }
}