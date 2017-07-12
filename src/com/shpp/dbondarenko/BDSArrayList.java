package com.shpp.dbondarenko;

import java.util.Arrays;
import java.util.Comparator;

/**
 * File: BDSArrayList.java
 * The class that implements the array list structure.
 * Created by Dmitro Bondarenko on 26.06.2017.
 */
public class BDSArrayList<G> {
    /**
     * The constant that corresponds to the initial length of the array for storing the items of the array list.
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * Array list size.
     */
    private int sizeArrayList;
    /**
     * An array for storing the elements of the array list.
     */
    private Object[] elements;
    /**
     * The size of the array.
     */
    private int capacity;

    /**
     * Create an empty array list.
     */
    public BDSArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Create an empty array list with a specified capacity.
     *
     * @param capacity The initial capacity for this array list.
     */
    public BDSArrayList(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        elements = new Object[capacity];
    }

    /**
     * Create a array list containing the elements in the specified of array list.
     *
     * @param bdsArrayList The array list whose elements are to be placed into this array list.
     */
    public BDSArrayList(BDSArrayList<? extends G> bdsArrayList) {
        this(bdsArrayList.size());
        addAll(bdsArrayList);
    }

    /**
     * Return a string representation of the contents of the specified array.
     *
     * @return A string representation of array.
     */
    @Override
    public String toString() {
        Object[] copyElement = new Object[sizeArrayList];
        System.arraycopy(elements, 0, copyElement, 0, sizeArrayList);
        return Arrays.toString(copyElement);
    }

    /**
     * Return a shallow copy of this BDSArrayList instance.
     *
     * @return A clone of this BDSArrayList instance.
     */
    public Object clone() {
        BDSArrayList<G> bdsArrayList = new BDSArrayList<>(sizeArrayList);
        for (int i = 0; i < sizeArrayList; i++) {
            bdsArrayList.add(getElement(i));
        }
        return bdsArrayList;
    }

    /**
     * Append the specified element to the end of this list.
     *
     * @param item The item to be appended to this list.
     * @return True if the operation was successful otherwise false.
     */
    public boolean add(G item) {
        if (sizeArrayList < capacity) {
            elements[sizeArrayList++] = item;
            return true;
        } else {
            changeCapacityOfArrayOfElements(DEFAULT_CAPACITY);
            elements[sizeArrayList++] = item;
            return true;
        }
    }

    /**
     * Insert the specified item at the specified position in this list.
     *
     * @param index The index at which the specified item is to be inserted.
     * @param item  The item to be inserted to this list.
     */
    public void add(int index, G item) {
        if (index < 0 || index > sizeArrayList) {
            throw new IndexOutOfBoundsException();
        } else {
            if (index == sizeArrayList) {
                add(item);
            } else {
                if (sizeArrayList < capacity - 1) {
                    insert(index, item);
                } else {
                    changeCapacityOfArrayOfElements(DEFAULT_CAPACITY);
                    insert(index, item);
                }
            }
        }
    }

    /**
     * Append all of the items in the specified collection to the end of this list.
     *
     * @param bdsArrayList The array list containing items to be added to this list.
     * @return True if this list changed as a result of the call.
     */
    public boolean addAll(BDSArrayList<? extends G> bdsArrayList) {
        if (bdsArrayList.isEmpty()) {
            throw new NullPointerException();
        } else {
            int sizeBDSArrayList = bdsArrayList.size();
            Object[] bdsArray = bdsArrayList.toArray();
            changeCapacityOfArrayOfElements(sizeBDSArrayList);
            System.arraycopy(bdsArray, 0, elements, sizeArrayList, sizeBDSArrayList);
            sizeArrayList += sizeBDSArrayList;
            return true;
        }
    }

    /**
     * Insert all of the items in the specified array list into this list, starting at the specified position.
     *
     * @param index        The index at which to insert the first item from the specified array list.
     * @param bdsArrayList The array list containing items to be added to this list.
     * @return True if this list changed as a result of the call.
     */
    public boolean addAll(int index, BDSArrayList<? extends G> bdsArrayList) {
        if (index < 0 || index > sizeArrayList) {
            throw new IndexOutOfBoundsException();
        } else if (bdsArrayList.isEmpty()) {
            throw new NullPointerException();
        } else {
            int sizeBDSArrayList = bdsArrayList.size();
            Object[] bdsArray = bdsArrayList.toArray();
            changeCapacityOfArrayOfElements(sizeBDSArrayList);
            Object[] copyElementsAfterIndex = new Object[sizeArrayList + 1 - index];
            System.arraycopy(elements, index, copyElementsAfterIndex,
                    0, sizeArrayList + 1 - index);
            System.arraycopy(bdsArray, 0, elements, index, sizeBDSArrayList);
            System.arraycopy(copyElementsAfterIndex, 0, elements,
                    index + sizeBDSArrayList, copyElementsAfterIndex.length);
            sizeArrayList += sizeBDSArrayList;
            return true;
        }
    }

    /**
     * Remove all of the elements from this list.
     */
    public void clear() {
        if (sizeArrayList > 0) {
            sizeArrayList = 0;
            elements = new Object[elements.length];
        }
    }

    /**
     * Return true if this list contains no items.
     *
     * @return True if this list contains no items.
     */
    public boolean isEmpty() {
        return sizeArrayList == 0;
    }

    /**
     * Return the item at the specified position in this list.
     *
     * @param index The index of the item to return.
     * @return The item at the specified position in this list.
     */
    public G get(int index) {
        if (index < 0 || index >= sizeArrayList) {
            throw new IndexOutOfBoundsException();
        } else {
            return getElement(index);
        }
    }

    /**
     * Replace the item at the specified position in this list with the specified element.
     *
     * @param index The index of the item to replace.
     * @param item  The item to be stored at the specified position.
     * @return The item previously at the specified position.
     */
    public G set(int index, G item) {
        if (index < 0 || index >= sizeArrayList) {
            throw new IndexOutOfBoundsException();
        } else {
            G oldItem = getElement(index);
            elements[index] = item;
            return oldItem;
        }
    }

    /**
     * Return the number of items in this list.
     *
     * @return The number of items in this list.
     */
    public int size() {
        return sizeArrayList;
    }

    /**
     * Return true if this queue contains the specified item.
     *
     * @param item The item whose presence in this list is to be tested.
     * @return True, if this queue contains a given item, otherwise it is false.
     */
    public boolean contains(Object item) {
        return indexOf(item) != -1;
    }

    /**
     * Return the index of the first occurrence of the specified item in this list,
     * or -1 if this list does not contain the item.
     *
     * @param item The item to search for.
     * @return The index of the first occurrence of the specified item in this list,
     * or -1 if this list does not contain the item.
     */
    public int indexOf(Object item) {
        if (item == null) {
            for (int i = 0; i < sizeArrayList; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
            return -1;
        } else {
            for (int i = 0; i < sizeArrayList; i++) {
                if (item.equals(elements[i])) {
                    return i;
                }
            }
            return -1;
        }
    }

    /**
     * Return the index of the last occurrence of the specified item in this list,
     * or -1 if this list does not contain the item.
     *
     * @param item The item to search for.
     * @return The index of the last occurrence of the specified item in this list,
     * or -1 if this list does not contain the item.
     */
    public int lastIndexOf(Object item) {
        if (item == null) {
            for (int i = sizeArrayList - 1; i > 0; i--) {
                if (elements[i] == null) {
                    return i;
                }
            }
            return -1;
        } else {
            for (int i = sizeArrayList - 1; i > 0; i--) {
                if (item.equals(elements[i])) {
                    return i;
                }
            }
            return -1;
        }
    }

    /**
     * Remove the item at the specified position in this list.
     *
     * @param index The index of the item to be removed.
     * @return The item that was removed from the list.
     */
    public G remove(int index) {
        if (index < 0 || index >= sizeArrayList) {
            throw new IndexOutOfBoundsException();
        } else {
            G deletedItem = get(index);
            if (index != sizeArrayList - 1) {
                System.arraycopy(elements, index + 1, elements, index, sizeArrayList - index);
            }
            elements[sizeArrayList] = null;
            sizeArrayList--;
            return deletedItem;
        }
    }

    /**
     * Remove the first occurrence of the specified item from this list, if it is present.
     *
     * @param item The item to be removed from this list, if present.
     * @return True if this list contained the specified item.
     */
    public boolean remove(Object item) {
        int indexItem = indexOf(item);
        if (indexItem != -1) {
            remove(indexItem);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return an array containing all of the items in this list in proper sequence (from first to last element).
     *
     * @return An array containing all of the items in this list in proper sequence.
     **/
    public Object[] toArray() {
        Object[] copyElement = new Object[sizeArrayList];
        System.arraycopy(elements, 0, copyElement, 0, sizeArrayList);
        return copyElement;
    }

    /**
     * Sort this list according to the order induced by the specified Comparator.
     **/
    public void sort(Comparator<? super G> comparator) {
        sortArrayElements(comparator);
    }

    /**
     * Sort array of items.
     *
     * @param comparator The comparator that will be used to order this array.
     **/
    @SuppressWarnings("unchecked")
    private void sortArrayElements(Comparator<? super G> comparator) {
        if (sizeArrayList != 1) {
            G[] sortElements = (G[]) new Object[sizeArrayList];
            System.arraycopy(elements, 0, sortElements, 0, sizeArrayList);
            if (comparator == null) {
                Arrays.sort(sortElements);
            } else {
                Arrays.sort(sortElements, comparator);
            }
            System.arraycopy(sortElements, 0, elements, 0, sortElements.length);
        }
    }

    /**
     * Get the item from the array by index.
     *
     * @return The item from the array by index.
     */
    @SuppressWarnings("unchecked")
    private G getElement(int index) {
        return (G) elements[index];
    }

    /**
     * Change the capacity of the array in which the list items are stored.
     *
     * @param valueOfCapacity The capacity value.
     */
    private void changeCapacityOfArrayOfElements(int valueOfCapacity) {
        Object[] copyElements = new Object[elements.length];
        System.arraycopy(elements, 0, copyElements, 0, elements.length);
        capacity += valueOfCapacity;
        elements = new Object[capacity];
        System.arraycopy(copyElements, 0, elements, 0, copyElements.length);
    }

    /**
     * Insert the specified element at the specified position in this array.
     *
     * @param index The index at which the specified element is to be inserted.
     * @param item  The element to be inserted to this array.
     */
    private void insert(int index, G item) {
        Object[] copyElementsAfterIndex = new Object[sizeArrayList + 1 - index];
        System.arraycopy(elements, index, copyElementsAfterIndex, 0, sizeArrayList + 1 - index);
        elements[index] = item;
        System.arraycopy(copyElementsAfterIndex, 0, elements, index + 1, copyElementsAfterIndex.length);
        sizeArrayList++;
    }
}