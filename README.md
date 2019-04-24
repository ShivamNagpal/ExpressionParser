# [Expression Parser](https://github.com/ShivamNagpal/ExpressionParser "Git Repository")

[![](https://img.shields.io/github/license/ShivamNagpal/ExpressionParser.svg)](https://github.com/ShivamNagpal/ExpressionParser/blob/master/LICENSE)
[![](https://jitpack.io/v/ShivamNagpal/ExpressionParser.svg)](https://jitpack.io/#ShivamNagpal/ExpressionParser/2.3)

A light-weight library which parses and evaluates the string expressions.

## Download
Step 1. Add the JitPack repository to your build file
```
allprojects {
    repositories {
	    ...
	    maven { url 'https://jitpack.io' }
    }
}
```
Step 2. Add the dependency
```
dependencies {
    implementation 'com.github.ShivamNagpal:ExpressionParser:2.3'
}
```

## Usage Illustration
``` java
String s = "<Put Expression String here>";
Expression expression = new Expression(s);
try {
    System.out.println(expression.evaluate());
} catch (ExpressionParserException e) {
    e.printStackTrace();
}
```

## Documentation
Class Expression (com.nagpal.shivam.expressionparser.Expression)

### Constructor Detail
#### Expression
``` java
public Expression(String expression)
```
constructs an Expression object initialized to the content of the specified string.
##### Parameters:
> expression - a string expression.

### Method Detail
#### evaluate
``` java
public double evaluate()
```
Evaluates the expression specified.
##### Returns:
> Returns the result obtained after evaluation of the specified expression.
##### Throws:
> ExpressionParserException (com.nagpal.shivam.expressionparser.ExpressionParserException) - if specified expression is invalid.
