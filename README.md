# Expression Parser
A light-weight library which parses and evaluates the string expressions.  
## Usage Illustration
``` java
Expression expression = new Expression("tan(89)");
try {
    System.out.println(expression.evaluate());
} catch (ExpressionParserException e) {
    e.printStackTrace();
}    
```