package com.shpp.dbondarenko;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * File: BDSStack.java
 * Created by Dmitro Bondarenko on 21.06.2017.
 */
public class BDSStack<G> {
    private int sizeStack;
    private Object[] elements;

    public BDSStack() {
        sizeStack = 0;
    }

    public G push(G item) {
        if (sizeStack == 0) {
            elements = new Object[++sizeStack];
            elements[sizeStack - 1] = item;
        } else {
            Object[] array = new Object[sizeStack];
            System.arraycopy(elements, 0, array, 0, sizeStack);
            elements = new Object[++sizeStack];
            System.arraycopy(array, 0, elements, 0, array.length);
            elements[sizeStack - 1] = item;
        }
       /* System.out.println(sizeStack);
        System.out.println(elements.length);*/
        System.out.println(Arrays.toString(elements));
        return item;
    }

    public G pop() {
        G peak;
        if (sizeStack == 0) {
            throw new EmptyStackException();
        } else {
            peak = elements(sizeStack - 1);
            Object[] array = new Object[sizeStack - 1];
            System.arraycopy(elements, 0, array, 0, sizeStack - 1);
            elements = new Object[--sizeStack];
            System.arraycopy(array, 0, elements, 0, sizeStack);
        }
        /*System.out.println(sizeStack);
        System.out.println(elements.length);*/
        System.out.println(Arrays.toString(elements));
        return peak;
    }

    public G peak() {
        G peak;
        if (sizeStack == 0) {
            throw new EmptyStackException();
        } else {
            peak = elements(sizeStack - 1);
        }
        /*System.out.println(sizeStack);
        System.out.println(elements.length);*/
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
                    return ++i;
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
