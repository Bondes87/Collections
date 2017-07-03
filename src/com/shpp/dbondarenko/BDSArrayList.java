package com.shpp.dbondarenko;

import java.util.Arrays;
import java.util.Comparator;

/**
 * File: BDSArrayList.java
 * Created by Dmitro Bondarenko on 26.06.2017.
 */
public class BDSArrayList<G> {
    private static final int DEFAULT_CAPACITY = 10;
    private int sizeArrayList;
    private Object[] elements;
    private int capacity;

    public BDSArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public BDSArrayList(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        elements = new Object[capacity];
    }

    public BDSArrayList(BDSArrayList<? extends G> bdsArrayList) {
        this(bdsArrayList.size());
        addAll(bdsArrayList);
    }

    @Override
    public String toString() {
        Object[] copyElement = new Object[sizeArrayList];
        System.arraycopy(elements, 0, copyElement, 0, sizeArrayList);
        return Arrays.toString(copyElement);
    }

    public Object clone() {
        BDSArrayList<G> bdsArrayList = new BDSArrayList<>(sizeArrayList);
        for (int i = 0; i < sizeArrayList; i++) {
            bdsArrayList.add(getElement(i));
        }
        return bdsArrayList;
    }

    public boolean add(G item) {
        if (sizeArrayList < capacity) {
            elements[sizeArrayList++] = item;
            return true;
        } else {
            increaseCapacityOfArrayOfElements(DEFAULT_CAPACITY);
            elements[sizeArrayList++] = item;
            return true;
        }
    }

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
                    increaseCapacityOfArrayOfElements(DEFAULT_CAPACITY);
                    insert(index, item);
                }
            }
        }
    }

    public boolean addAll(BDSArrayList<? extends G> bdsArrayList) {
        if (bdsArrayList.isEmpty()) {
            throw new NullPointerException();
        } else {
            int sizeBDSArrayList = bdsArrayList.size();
            Object[] bdsArray = bdsArrayList.toArray();
            increaseCapacityOfArrayOfElements(sizeBDSArrayList);
            System.arraycopy(bdsArray, 0, elements, sizeArrayList, sizeBDSArrayList);
            sizeArrayList += sizeBDSArrayList;
            return true;
        }
    }

    public boolean addAll(int index, BDSArrayList<? extends G> bdsArrayList) {
        if (index < 0 || index > sizeArrayList) {
            throw new IndexOutOfBoundsException();
        } else if (bdsArrayList.isEmpty()) {
            throw new NullPointerException();
        } else {
            int sizeBDSArrayList = bdsArrayList.size();
            Object[] bdsArray = bdsArrayList.toArray();
            increaseCapacityOfArrayOfElements(sizeBDSArrayList);
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

    public void clear() {
        if (sizeArrayList > 0) {
            sizeArrayList = 0;
            elements = new Object[elements.length];
        }
    }

    public boolean isEmpty() {
        return sizeArrayList == 0;
    }

    public G get(int index) {
        if (index < 0 || index >= sizeArrayList) {
            throw new IndexOutOfBoundsException();
        } else {
            return getElement(index);
        }
    }

    public G set(int index, G item) {
        if (index < 0 || index >= sizeArrayList) {
            throw new IndexOutOfBoundsException();
        } else {
            G oldItem = getElement(index);
            elements[index] = item;
            return oldItem;
        }
    }

    public int size() {
        return sizeArrayList;
    }

    public boolean contains(Object item) {
        return indexOf(item) != -1;
    }

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

    public G remove(int index) {
        if (index < 0 || index >= sizeArrayList) {
            throw new IndexOutOfBoundsException();
        } else {
            G deletedItem = get(index);
            Object[] copyElementsAfterIndex = new Object[sizeArrayList + 1 - index];
            System.arraycopy(elements, index + 1, copyElementsAfterIndex,
                    0, sizeArrayList + 1 - index);
            System.arraycopy(copyElementsAfterIndex, 0, elements, index, copyElementsAfterIndex.length);
            elements[sizeArrayList] = null;
            sizeArrayList--;
            return deletedItem;
        }
    }

    public boolean remove(Object item) {
        int indexItem = indexOf(item);
        if (indexItem != -1) {
            remove(indexItem);
            return true;
        } else {
            return false;
        }
    }

    public Object[] toArray() {
        Object[] copyElement = new Object[sizeArrayList];
        System.arraycopy(elements, 0, copyElement, 0, sizeArrayList);
        return copyElement;
    }

    public void sort(Comparator<? super G> comparator) {
        sortArrayElements(comparator);
    }

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

    @SuppressWarnings("unchecked")
    private G getElement(int index) {
        return (G) elements[index];
    }

    private void increaseCapacityOfArrayOfElements(int valueOfIncreaseInCapacity) {
        Object[] copyElements = new Object[elements.length];
        System.arraycopy(elements, 0, copyElements, 0, elements.length);
        capacity += valueOfIncreaseInCapacity;
        elements = new Object[capacity];
        System.arraycopy(copyElements, 0, elements, 0, copyElements.length);
    }

    private void insert(int index, G item) {
        Object[] copyElementsAfterIndex = new Object[sizeArrayList + 1 - index];
        System.arraycopy(elements, index, copyElementsAfterIndex, 0, sizeArrayList + 1 - index);
        elements[index] = item;
        System.arraycopy(copyElementsAfterIndex, 0, elements, index + 1, copyElementsAfterIndex.length);
        sizeArrayList++;
    }
}