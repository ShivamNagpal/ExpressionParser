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

class TokenNode {
    static final byte TYPE_OPERAND = 1;
    static final byte TYPE_OPERATOR = 2;
    static final byte TYPE_PARENTHESIS = 3;

    static final byte SUB_TYPE_OPERAND_LITERAL = 11;
    static final byte SUB_TYPE_OPERAND_SYMBOL = 12;
    static final byte SUB_TYPE_OPERAND_FUNCTION = 13;

    static final byte SUB_TYPE_OPERATOR_UNARY = 21;
    static final byte SUB_TYPE_OPERATOR_BINARY = 22;

    static final byte SUB_TYPE_PARENTHESIS_LEFT = 31;
    static final byte SUB_TYPE_PARENTHESIS_RIGHT = 32;

    private byte type;
    private byte subType;
    private String token;

    TokenNode(byte type, byte subType, String token) {
        this.type = type;
        this.subType = subType;
        this.token = token;
    }

    byte getType() {
        return type;
    }

    byte getSubType() {
        return subType;
    }

    String getToken() {
        return token;
    }
}
