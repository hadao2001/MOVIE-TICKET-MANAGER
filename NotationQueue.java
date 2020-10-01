package Notation;

import java.util.ArrayList;

/**
 * CMSC 204 Assignment 2
 * Class: MyQueue
 * 
 * @author Ha T Dao
 * 
 * Description: MyQueue will implement the QueueInterface. 
 * 
 * @param <T> data type
 */

public class NotationQueue<T> implements QueueInterface<T>{
    
    private int sizeOfQueue; //Size of queue
    private ArrayList<T> queue;  //Data structure that behaves as queue
    private int numElements; //Number of elements in queue
    
    
    /**
     * Default constructor - uses a default as the size of the queue
     */
    public NotationQueue() {
        queue = new ArrayList<>();
        numElements = 0;
        sizeOfQueue = 1000;
    }

    /**
     * One-arg constructor that takes an int as the size of the queue
     * @param i maximum queue capacity
     */
    public NotationQueue(int i) {
    	numElements = 0;
        queue = new ArrayList<>(i);
        this.sizeOfQueue = i;
    }

    /**
	 * isEmpty
	 * Determines if Queue is empty
	 * @return true if Queue is empty, false if not
	 */
	public boolean isEmpty() {
		
		if(numElements == 0) {
			return true;
		} 
		else {
			return false;
		}
	}

	/**
	 * isFull
	 * Determines if the Queue is empty
	 * @return true if Queue is the size of Queue, false if not
	 */
	public boolean isFull() {
		
		if(numElements == sizeOfQueue) {
			return true;
		} 
		else {
			return false;
		}
	}

    @Override
    public T dequeue() throws QueueUnderflowException {
        if (!isEmpty()) {
            T element = queue.get(0);
            queue.remove(0);
            return element;
        } else { 
            throw new QueueUnderflowException();
        }
    }

    /**
	 * Number of elements in the Queue
	 * @return the number of elements in the Queue
	 */
	public int size() {
		//System.out.println("Inside size() - The size = " + numElements);
		return numElements;
		
	}

    @Override
    public boolean enqueue(T e) throws QueueOverflowException {
        if (!isFull()) {
            return queue.add(e);
        } else {
            throw new QueueOverflowException();
        }
    }

    @Override
    public String toString() {
        String strQueue = "";
        for (T e : queue) {
            strQueue += e;
        }
        return strQueue;
    }
    
    @Override
    public String toString(String delimiter) {
        String strQueue = "";
        for (T e : queue) {
            strQueue += e + delimiter;
        }
        return strQueue.substring(0, strQueue.length() - 1);
    }

    @Override
    public void fill(ArrayList<T> list) {
        for (T element : list) {
            enqueue(element);
        }
    }
    
}