package com.shpp.dbondarenko;

import java.util.NoSuchElementException;

/**
 * File: BDSQueue.java
 * The class that implements the queue structure.
 * Created by Dmitro Bondarenko on 22.06.2017.
 */
public class BDSQueue<G> {
    /**
     * The constant that corresponds to the initial length of the array for storing the items of the queue.
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * Queue size.
     */
    private int sizeQueue;
    /**
     * An array for storing the elements of the queue.
     */
    private Object[] elements;
    /**
     * The size of the array.
     */
    private int capacity;

    /**
     * Create an empty Queue.
     */
    public BDSQueue() {
        capacity = DEFAULT_CAPACITY;
        elements = new Object[capacity];
    }

    /**
     * Insert the specified element into this queue if it is possible.
     *
     * @param item The item that is placed on top of the queue.
     * @return True if the element was added to this queue, else false.
     */
    public boolean offer(G item) {
        if (item == null) {
            return false;
        } else {
            if (sizeQueue < capacity) {
                elements[sizeQueue++] = item;
            } else {
                Object[] copyElements = new Object[elements.length];
                System.arraycopy(elements, 0, copyElements, 0, elements.length);
                capacity += DEFAULT_CAPACITY;
                elements = new Object[capacity];
                System.arraycopy(copyElements, 0, elements, 0, copyElements.length);
                elements[sizeQueue++] = item;
            }
            return true;
        }
    }

    /**
     * Insert the specified element into this queue if it is possible.
     *
     * @param item The item that is placed on top of the queue.
     * @return True upon success and throwing an IllegalStateException if no space is currently available.
     */
    public boolean add(G item) {
        if (offer(item)) {
            return true;
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Retrieve, but does not remove, the head of this queue, or return null if this queue is empty.
     *
     * @return The head of this queue, or null if this queue is empty.
     */
    @SuppressWarnings("unchecked")
    public G peek() {
        if (sizeQueue == 0) {
            return null;
        } else {
            return (G) elements[0];
        }
    }

    /**
     * Retrieve, but does not remove, the head of this queue, or return NoSuchElementException if this queue is empty.
     *
     * @return The head of this queue, or NoSuchElementException if this queue is empty.
     */
    public G element() {
        G element = peek();
        if (element == null) {
            throw new NoSuchElementException();
        } else {
            return element;
        }
    }

    /**
     * Retrieve and remove the head of this queue, or return null if this queue is empty.
     *
     * @return The head of this queue, or null if this queue is empty.
     */
    @SuppressWarnings("unchecked")
    public G pool() {
        G queueHead;
        if (sizeQueue == 0) {
            return null;
        } else {
            queueHead = (G) elements[0];
            System.arraycopy(elements, 1, elements, 0, elements.length - 1);
            return queueHead;
        }
    }

    /**
     * Retrieve and remove the head of this queue, or return NoSuchElementException if this queue is empty.
     *
     * @return The head of this queue, or NoSuchElementException if this queue is empty.
     */
    public G remove() {
        G queueHead = pool();
        if (queueHead == null) {
            throw new NoSuchElementException();
        } else {
            return queueHead;
        }
    }
}
