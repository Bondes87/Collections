package com.shpp.dbondarenko;

import java.util.EmptyStackException;

/**
 * File: BDSStack.java
 * The class that implements the stack structure.
 * Created by Dmitro Bondarenko on 21.06.2017.
 */
public class BDSStack<G> {
    /**
     * The constant that corresponds to the initial length of the array for storing the items of the stack.
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * Stack size.
     */
    private int sizeStack;
    /**
     * An array for storing the elements of the stack.
     */
    private Object[] elements;
    /**
     * The size of the array.
     */
    private int capacity;

    /**
     * Create an empty Stack.
     */
    public BDSStack() {
        capacity = DEFAULT_CAPACITY;
        elements = new Object[capacity];
    }

    /**
     * Place the item on the top of the stack and return that item.
     *
     * @param item The item that is placed on top of the stack.
     * @return The item.
     */
    public G push(G item) {
        if (sizeStack < capacity) {
            elements[sizeStack++] = item;
        } else {
            Object[] copyElements = new Object[elements.length];
            System.arraycopy(elements, 0, copyElements, 0, elements.length);
            capacity += DEFAULT_CAPACITY;
            elements = new Object[capacity];
            System.arraycopy(copyElements, 0, elements, 0, copyElements.length);
            elements[sizeStack++] = item;
        }
        return item;
    }

    /**
     * Remove the item at the top of this stack and return that item as the value of this function.
     *
     * @return The item at the top of this stack.
     */
    public G pop() {
        G peak;
        if (sizeStack == 0) {
            throw new EmptyStackException();
        } else {
            peak = elements(--sizeStack);
            elements[sizeStack] = null;
        }
        return peak;
    }

    /**
     * Look at the item at the top of this stack without removing it from the stack.
     *
     * @return The item at the top of this stack.
     */
    public G peek() {
        G peak;
        if (sizeStack == 0) {
            throw new EmptyStackException();
        } else {
            peak = elements(sizeStack - 1);
        }
        return peak;
    }

    /**
     * Check whether this stack is empty.
     *
     * @return True if and only if this stack contains no items; false otherwise..
     */
    public boolean empty() {
        return sizeStack == 0;
    }

    /**
     * Check for an item in the stack.
     *
     * @return Returns the position of the element from the top of the stack,
     * if such an element exists on the stack, else -1.
     */
    public int search(Object o) {
        if (sizeStack == 0) {
            throw new EmptyStackException();
        } else {
            for (int i = 0; i < elements.length; i++) {
                if (elements[i].equals(o)) {
                    return sizeStack - i;
                }
            }
        }
        return -1;
    }

    /**
     * Get the element from the array by index.
     *
     * @return The element from the array by index.
     */
    @SuppressWarnings("unchecked")
    private G elements(int index) {
        return (G) elements[index];
    }
}
