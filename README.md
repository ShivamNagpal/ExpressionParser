# [Expression Parser](https://github.com/ShivamNagpal/ExpressionParser "Git Repository")
A light-weight library which parses and evaluates the string expressions.  
  
## Usage Illustration
``` java
Expression expression = new Expression(/* String Expression */);
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