package Notation;

import java.util.ArrayList;

/**
 * CMSC 204 Assignment 2
 * Class: MyStack
 * 
 * @author Ha T DAo
 * 
 * Description: MyStack will implement the StackInterface. 
 * Implementation of a generic stack data structure.
 
 * @param <T> data type
 */

public class NotationStack<T> implements StackInterface<T> {
    
   
    private ArrayList<T> stack; //Data structure that behaves as stack
    private int sizeOfStack; //Capacity of stack
    private int numStackElements; //Number of elements on Stack
    private int top;
    
    /**
     * Default constructor - uses default as the size of the stack
     */
    public NotationStack() {
        stack = new ArrayList<>();
        sizeOfStack = 1000;
    }
    
    /**
     * One-arg constructor that takes in an int as the size of the stack
     * @param capacity maximum stack capacity
     */
    public NotationStack(int capacity) {
        stack = new ArrayList<>(capacity);
        this.sizeOfStack = capacity;
    }

    /**
	 * isEmpty
	 * Determines if Stack is empty
	 * @return true if Stack is empty, false if not
	 */
	public boolean isEmpty() {
		if(top == -1) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * isFull
	 * Determines if Stack is full
	 * @return true if Stack is full, false if not
	 */
	public boolean isFull() {
		if(sizeOfStack == numStackElements) {
			return true;
		}
		else {
			return false;
		}
		
	}

	/**
	 * pop
	 * Deletes and returns the element at the top of the Stack
	 * @return the element at the top of the Stack
	 */
	@Override
    public T pop() throws StackUnderflowException {
        if (!isEmpty()) {
            T element = stack.get(size() - 1);
            stack.remove(size() - 1);
            return element;
        } else { 
            throw new StackUnderflowException();
        }
    }

    /**
	 * peek
	 * Returns the element at the top of the Stack, does not pop it off the Stack
	 * @return the element at the top of the Stack
	 */
    public T peek() throws StackUnderflowException {
        if (!isEmpty()) {
            return stack.get(size() - 1);
        } else {
            throw new StackUnderflowException();
        }
    }

    /**
	 * size
	 * Number of elements in the Stack
	 * @return the number of elements in the Stack
	 */
    public int size() {
        return stack.size();
    }

    /**
	 * push
	 * Adds an element to the top of the Stack
	 * @param e the element to add to the top of the Stack
	 * @return true if the add was successful, false if not
	 */
    public boolean push(T e) throws StackOverflowException {
        if (!isFull()) {
            return stack.add(e);
        } else {
            throw new StackOverflowException();
        }
    }

    /**
	 * toString
	 * Returns the elements of the Stack in a string from bottom to top, the beginning 
	 * of the String is the bottom of the stack
	 * @return an string which represent the Objects in the Stack from bottom to top
	 */
    public String toString() {
        String strStack = "";
        for (T e : stack) {
            strStack += e;
        }
        return strStack;
    }
    
    @Override
    public String toString(String delimiter) {
        String strStack = "";
        for (T e : stack) {
            strStack += e + delimiter;
        }
        return strStack.substring(0, strStack.length() - 1);
    }

    @Override
    public void fill(ArrayList<T> list) throws StackOverflowException {
        for (T element : list) {
            push(element);
        }
    }

	@Override
	public T top() throws StackUnderflowException {
		// TODO Auto-generated method stub
		return null;
	}
    
}