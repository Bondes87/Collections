package com.shpp.dbondarenko;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * File: BDSPriorityQueue.java
 * Created by Dmitro Bondarenko on 23.06.2017.
 */
public class BDSPriorityQueue<G> extends BDSQueue<G> {

    private static final int DEFAULT_CAPACITY = 10;
    private int sizeQueue;
    private Object[] elements;
    private int capacity;
    private Comparator<? super G> comparator;

    public BDSPriorityQueue() {
        this(DEFAULT_CAPACITY);
    }

    public BDSPriorityQueue(int capacity) {
        this(capacity, null);
    }

    public BDSPriorityQueue(int capacity, Comparator<? super G> comparator) {
        if (capacity < 1) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        elements = new Object[capacity];
        this.comparator = comparator;
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
                elements = new Object[capacity + DEFAULT_CAPACITY];
                System.arraycopy(copyElements, 0, elements, 0, copyElements.length);
                elements[sizeQueue++] = item;
            }
            sortArrayElements();
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

    public boolean contains(G item) {
        if (item != null) {
            for (int i = 0; i < sizeQueue; i++) {
                if (item.equals(elements[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    public int size() {
        return sizeQueue;
    }

    public void clear() {
        if (sizeQueue > 0) {
            while (sizeQueue >= 0) {
                elements[sizeQueue--] = null;
            }
            sizeQueue++;
        }

    }

    public Object[] toArray() {
        Object[] copyElement = new Object[sizeQueue];
        System.arraycopy(elements, 0, copyElement, 0, sizeQueue);
        return copyElement;
    }

    @SuppressWarnings("unchecked")
    private void sortArrayElements() {
        if (sizeQueue != 1) {
            G[] sortElements = (G[]) new Object[sizeQueue];
            System.arraycopy(elements, 0, sortElements, 0, sizeQueue);
            if (comparator == null) {
                Arrays.sort(sortElements);
            } else {
                Arrays.sort(sortElements, comparator);
            }
            System.arraycopy(sortElements, 0, elements, 0, sortElements.length);
        }
    }
}
