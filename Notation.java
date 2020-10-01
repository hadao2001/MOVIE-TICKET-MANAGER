package Notation;

import java.util.HashMap;

/**
 * CMSC 204 Assignment 2
 * Class Notation
 * 
 * @author Ha T DAo
 * 
 * Description: The Notation class will have a method infixToPostfix to convert infix notation to postfix notation that will 
 * take in a string and return a string, a method postfixToInfix to convert postfix notation to infix notation that will take 
 * in a string and return a string, and a method to evaluatePostfix to evaluate the postfix expression. It will take in a string 
 * and return a double. A method to evaluateInfix to evaluate the infix expression. It will take in a string and return a double. 
 * 
 */
public class Notation {
    
    /**
     * No-arg constructor.
     */
    public Notation() {
        
    }
    
    /**
     * convertInfixToPostfix
	 * @param Take in String of InFix to convert to PostFix
	 * 
     * @return  String postfix expression
     * @throws InvalidNotationFormatException 
     */
    public static String convertInfixToPostfix(String i) throws InvalidNotationFormatException {
        NotationStack<String> operatorStack = new NotationStack<>(i.length());
        operatorStack.push("");
        
        NotationQueue<String> postfixSolution = new NotationQueue<>(i.length());
        postfixSolution.enqueue("");
        
        HashMap<String, Integer> precedence = new HashMap<>();
        precedence.put("+", 1);
        precedence.put("-", 2);
        precedence.put("*", 3);
        precedence.put("/", 4);
            
        boolean TopOperator = false, isHigherPrecedence;
        
        for (char s : i.toCharArray()) {
                        
            String nextChar = String.valueOf(s);
            
            for (String operator : precedence.keySet()) {
                TopOperator = operatorStack.peek().equals(operator);
                if (TopOperator) {
                    break;
                }
            }

            try {
                isHigherPrecedence = precedence.get(operatorStack.peek()) 
                        >= precedence.get(nextChar);
            } catch (NullPointerException e) {
                isHigherPrecedence = false;
            }
            
            if (Character.isDigit(s)) {
                postfixSolution.enqueue(nextChar);
            } else if (s == '(') {
                operatorStack.push(nextChar);
            } else if (s == '+' || s == '-' || s == '*' || s == '/') {
                if (operatorStack.peek().equals("+") || 
                    operatorStack.peek().equals("-") ||
                    operatorStack.peek().equals("*") ||
                    operatorStack.peek().equals("/")) {
                    throw new InvalidNotationFormatException();
                }
                if (TopOperator && isHigherPrecedence) {
                    postfixSolution.enqueue(operatorStack.pop());
                }
                operatorStack.push(nextChar);
            } else if (s == ')') {

                try {
                    while (!operatorStack.peek().equals("(")) {
                        postfixSolution.enqueue(operatorStack.pop());
                    }
                } catch (StackUnderflowException e) {
                    throw new InvalidNotationFormatException();
                }
                operatorStack.pop();
            }                        
        } 
        
        while (!operatorStack.isEmpty() && (TopOperator)) {
            postfixSolution.enqueue(operatorStack.pop());
        }
        
        for (char c : postfixSolution.toString().toCharArray()) {
            if ( c == '(') {
                throw new InvalidNotationFormatException();
            }
        }
                
        return postfixSolution.toString();
    }
    
    /**
     * This method takes a postfix expression 
     * and converts it to an infix expression.
     * @param postfix postfix expression to be converted
     * @return infix expression
     */
    public static String convertPostfixToInfix(String postfix) {
        NotationStack<String> infixSolution = new NotationStack<>(postfix.length());
        infixSolution.push("");
        
        for (char s : postfix.toCharArray()) {
            
            String nextChar = String.valueOf(s);
            
            if (Character.isDigit(s)) {
                infixSolution.push(nextChar);
            } else if (s == '+' || s == '-' || s == '*' || s == '/') {
                if (infixSolution.size() < 3) {
                    throw new InvalidNotationFormatException();
                }
                String first = infixSolution.pop(), second = infixSolution.pop();
                String result = '(' + second + s + first + ')';
                infixSolution.push(result);
            }
        }
        
        if (infixSolution.size() > 2) {
            throw new InvalidNotationFormatException();
        }
        
        return infixSolution.toString();
    }
    
    /**
     * This method takes a infix expression to find its result.
     * @param infixExpr infix expression to be evaluated
     * @return result value of infix expression
     */
    public static double evaluateInfixExpression(String infixExpr) {
        
        return evaluatePostfixExpression(convertInfixToPostfix(infixExpr));
    }
    
    /**
     * This method takes a postfix expression to find its result.
     * @param postFix postfix expression to be evaluated
     * @return result value of infix expression
     */
    public static double evaluatePostfixExpression(String postFix) {
        NotationStack<Double> value = new NotationStack<>(postFix.length());
        value.push(Double.NaN);
        
        for (char s : postFix.toCharArray()) {
                        
            if (Character.isDigit(s)) {
                value.push(Double.parseDouble(String.valueOf(s)));
            } else if (s == '+' || s == '-' || s == '*' || s == '/') {
                if (value.size() < 3) {
                    throw new InvalidNotationFormatException();
                }
                double first = value.pop(), second = value.pop();
                double result = (s == '+') ? second + first : 
                        (s == '-') ? second - first : 
                        (s == '*') ? second * first :
                        (s == '/') ? second / first : 0;
                value.push(result);
            } 
            
        }
        
        if (value.size() > 2) {
            throw new InvalidNotationFormatException();
        }
        
        return value.peek();
    }
}