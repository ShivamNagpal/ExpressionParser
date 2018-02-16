# Expression Parser
A light-weight library which parses and evaluates the string expressions.  
## Usage Illustration
```
Expression expression = new Expression(/* String Expression */);  
        try {  
            System.out.println(expression.evaluate());  
        } catch (ExpressionParserException e) {  
            e.printStackTrace();  
        }        
```