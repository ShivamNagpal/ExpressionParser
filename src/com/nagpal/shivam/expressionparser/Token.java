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

class Token {

    static byte currentTokenSubType;

    static Boolean isSpace(char ch) {
        return (ch == ' ' || ch == '\t' || ch == '\n');
    }

    static Boolean isDecimal(char ch) {
        return (ch == '.');
    }

    static Boolean isOperator(char ch) {
        if (ch == '!' || ch == '%') {
            currentTokenSubType = TokenNode.SUB_TYPE_OPERATOR_UNARY;
            return true;
        } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '#' || ch == '^' || ch == 'E' || ch == 'P' || ch == 'C') {
            currentTokenSubType = TokenNode.SUB_TYPE_OPERATOR_BINARY;
            return true;
        }
        return false;
    }

    static Boolean isParenthesis(char ch) {

        if (ch == '(') {
            currentTokenSubType = TokenNode.SUB_TYPE_PARENTHESIS_LEFT;
            return true;
        } else if (ch == ')') {
            currentTokenSubType = TokenNode.SUB_TYPE_PARENTHESIS_RIGHT;
            return true;
        }
        return false;
    }

    static Boolean isLiteralOperand(char ch) {
        return Character.isDigit(ch);
    }

    static Boolean isDerivedOperand(char ch) {
        return (Character.isLetter(ch) && Character.isLowerCase(ch));
    }

    static byte precedence(String opr) {
        switch (opr) {
            case "(":
                return 0;

            case "+":
            case "-":
                return 1;

            case "*":
            case "/":
            case "#":
            case "E":
                return 2;

            case "^":
                return 3;

            case "!":
            case "%":
            case "P":
            case "C":
                return 4;
        }
        return -1;
    }

}
