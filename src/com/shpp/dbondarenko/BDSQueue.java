package com.shpp.dbondarenko;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * File: BDSQueue.java
 * Created by Dmitro Bondarenko on 22.06.2017.
 */
public class BDSQueue<G> {
    private int sizeQueue;
    private Object[] elements;
    private int capacity;

    public BDSQueue() {
        capacity = 10;
        elements = new Object[capacity];
    }

    public boolean offer(G item) {
        if (item == null) {
            return false;
        } else {
            if (sizeQueue < capacity) {
                elements[sizeQueue++] = item;
            } else {
                Object[] copyElements = new Object[elements.length];
                System.arraycopy(elements, 0, copyElements, 0, elements.length);
                elements = new Object[capacity * 2];
                System.arraycopy(copyElements, 0, elements, 0, copyElements.length);
                elements[sizeQueue++] = item;
            }
            System.out.println(Arrays.toString(elements));
            return true;
        }
    }

    public boolean add(G item) {
        if (offer(item)) {
            return true;
        } else {
            throw new NullPointerException();
        }
    }

    @SuppressWarnings("unchecked")
    public G peek() {
        if (sizeQueue == 0) {
            return null;
        } else {
            System.out.println(Arrays.toString(elements));
            return (G) elements[0];
        }
    }

    public G element() {
        G element = peek();
        if (element == null) {
            throw new NoSuchElementException();
        } else {
            return element;
        }
    }

    @SuppressWarnings("unchecked")
    public G pool() {
        G queueHead;
        if (sizeQueue == 0) {
            return null;
        } else {
            queueHead = (G) elements[0];
            System.arraycopy(elements, 1, elements, 0, elements.length - 1);
            System.out.println(Arrays.toString(elements));
            return queueHead;
        }
    }

    public G remove() {
        G queueHead = pool();
        if (queueHead == null) {
            throw new NoSuchElementException();
        } else {
            return queueHead;
        }
    }
}
