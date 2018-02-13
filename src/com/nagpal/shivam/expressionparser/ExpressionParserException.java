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

public class ExpressionParserException extends Exception {
    static final String INVALID_BINARY_OPERATOR = "Invalid Binary Operator.";
    static final String INVALID_EXPRESSION = "Invalid Expression.";
    static final String INVALID_FUNCTION = "Invalid Function.";
    static final String INVALID_OPERAND = "Invalid Operand.";
    static final String INVALID_SYMBOL = "Invalid Symbol.";
    static final String INVALID_TOKEN = "Invalid Token.";
    static final String INVALID_UNARY_OPERATOR = "Invalid Unary Operator.";
    static final String INSUFFICIENT_OPERATORS = "Insufficient Operators.";
    static final String MATH_ERROR = "Math Error.";

    ExpressionParserException(String message) {
        super(message);
    }
}
