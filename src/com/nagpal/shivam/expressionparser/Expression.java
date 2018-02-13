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

import java.util.ArrayList;

public class Expression {
    public static final byte ANGLE_UNITS_RADIAN = 1;
    public static final byte ANGLE_UNITS_DEGREE = 2;
    static byte angleUnits = ANGLE_UNITS_RADIAN;
    static boolean normalizeTrigonometricFunctions = false;
    private String expression;

    public Expression(String expression) {
        this.expression = expression;
    }

    public static void setAngleUnits(byte angleUnits) {
        Expression.angleUnits = angleUnits;
    }

    public static void setNormalizeTrigonometricFunctions(boolean normalizeTrigonometricFunctions) {
        Expression.normalizeTrigonometricFunctions = normalizeTrigonometricFunctions;
    }

    public double evaluate() throws ExpressionParserException {
        ArrayList<TokenNode> tokenNodeArrayList = Evaluation.tokenizeInfix(expression);
        tokenNodeArrayList = Evaluation.infixToPostfix(tokenNodeArrayList);
        return Evaluation.postfixEvaluation(tokenNodeArrayList);
    }
}
