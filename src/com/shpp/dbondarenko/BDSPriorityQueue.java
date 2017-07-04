package com.shpp.dbondarenko;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * File: BDSPriorityQueue.java
 * The class that implements the priority queue structure.
 * Created by Dmitro Bondarenko on 23.06.2017.
 */
public class BDSPriorityQueue<G> extends BDSQueue<G> {
    /**
     * The constant that corresponds to the initial length of the array for storing the items of the priority queue.
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * Priority queue size.
     */
    private int sizeQueue;
    /**
     * An array for storing the elements of the priority queue.
     */
    private Object[] elements;
    /**
     * The size of the array.
     */
    private int capacity;
    /**
     * The comparator for sorting of priority queue.
     */
    private Comparator<? super G> comparator;

    /**
     * Create an empty priority queue.
     */
    public BDSPriorityQueue() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Create an empty priority queue with a specified capacity.
     *
     * @param capacity The initial capacity for this priority queue.
     */
    public BDSPriorityQueue(int capacity) {
        this(capacity, null);
    }

    /**
     * Create an empty priority queue with a specified capacity and comparator.
     *
     * @param capacity   The initial capacity for this priority queue.
     * @param comparator The comparator that will be used to order this priority queue.
     *                   If null, the natural ordering of the elements will be used.
     */
    public BDSPriorityQueue(int capacity, Comparator<? super G> comparator) {
        if (capacity < 1) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        elements = new Object[capacity];
        this.comparator = comparator;
    }

    /**
     * Create a priority queue containing the elements in the specified of priority the queue..
     *
     * @param bdsPriorityQueue The priority queue whose elements are to be placed into this priority queue.
     */
    public BDSPriorityQueue(BDSPriorityQueue<? extends G> bdsPriorityQueue) {
        if (bdsPriorityQueue == null) {
            throw new NullPointerException();
        } else {
            initBDSPriorityQueue(bdsPriorityQueue);
        }
    }

    /**
     * Return a string representation of the contents of the specified array.
     *
     * @return A string representation of array.
     */
    @Override
    public String toString() {
        Object[] copyElement = new Object[sizeQueue];
        System.arraycopy(elements, 0, copyElement, 0, sizeQueue);
        return Arrays.toString(copyElement);
    }

    /**
     * Insert the specified element into this priority queue..
     *
     * @param item The item to add.
     * @return True if the operation was successful otherwise false.
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
            sortArrayElements();
            return true;
        }
    }

    /**
     * Insert the specified element into this priority queue.
     *
     * @param item The item to add.
     * @return True if the operation was successful otherwise NullPointerException.
     */
    public boolean add(G item) {
        if (offer(item)) {
            return true;
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Retrieve, but does not remove, the head of this queue, or return null if this queue is empty..
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
     * Retrieve, but does not remove, the head of this queue,
     * or return NoSuchElementException if this queue is empty.
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
     * Retrieve and removes the head of this queue, or return null if this queue is empty.
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

    /**
     * Return true if this queue contains the specified element.
     *
     * @param item The element for search.
     * @return True, if this queue contains a given element, otherwise it is false.
     */
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

    /**
     * Return the number of elements in this collection.
     *
     * @return The number of elements in this collection
     **/
    public int size() {
        return sizeQueue;
    }

    /**
     * Remove all of the elements from this priority queue.
     **/
    public void clear() {
        if (sizeQueue > 0) {
            while (sizeQueue >= 0) {
                elements[sizeQueue--] = null;
            }
            sizeQueue++;
        }
    }

    /**
     * Return an array containing all of the elements in this queue. The elements are in no particular order.
     *
     * @return An array containing all of the elements in this queue.
     **/
    public Object[] toArray() {
        Object[] copyElement = new Object[sizeQueue];
        System.arraycopy(elements, 0, copyElement, 0, sizeQueue);
        return copyElement;
    }

    /**
     * Return the comparator used to order the elements in this queue,
     * or null if this queue is sorted according to the natural ordering of its elements.
     *
     * @return The comparator used to order this queue, or null if this queue
     * is sorted according to the natural ordering of its elements.
     **/
    public Comparator<? super G> comparator() {
        return comparator;
    }

    /**
     * Create a priority queue based on this queue.
     *
     * @param bdsPriorityQueue The priority queue based on which a new priority queue is created.
     **/
    @SuppressWarnings("unchecked")
    private void initBDSPriorityQueue(BDSPriorityQueue<? extends G> bdsPriorityQueue) {
        comparator = (Comparator<? super G>) bdsPriorityQueue.comparator();
        elements = bdsPriorityQueue.toArray();
        sizeQueue = bdsPriorityQueue.size();
        capacity = bdsPriorityQueue.size() + DEFAULT_CAPACITY;
    }

    /**
     * Sort array of items.
     **/
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
