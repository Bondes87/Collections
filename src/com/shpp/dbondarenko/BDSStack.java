package com.shpp.dbondarenko;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * File: BDSStack.java
 * Created by Dmitro Bondarenko on 21.06.2017.
 */
public class BDSStack<G> {
    private static final int DEFAULT_CAPACITY = 10;
    private int sizeStack;
    private Object[] elements;
    private int capacity;

    public BDSStack() {
        capacity = DEFAULT_CAPACITY;
        elements = new Object[capacity];
    }

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
        System.out.println(Arrays.toString(elements));
        return item;
    }

    public G pop() {
        G peak;
        if (sizeStack == 0) {
            throw new EmptyStackException();
        } else {
            peak = elements(--sizeStack);
            elements[sizeStack] = null;
        }
        System.out.println(Arrays.toString(elements));
        return peak;
    }

    public G peek() {
        G peak;
        if (sizeStack == 0) {
            throw new EmptyStackException();
        } else {
            peak = elements(sizeStack - 1);
        }
        System.out.println(Arrays.toString(elements));
        return peak;
    }

    public boolean empty() {
        return sizeStack == 0;
    }

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

    @SuppressWarnings("unchecked")
    private G elements(int index) {
        return (G) elements[index];
    }
}
