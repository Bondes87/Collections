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

    @Override
    public String toString() {
        return "BDSArrayList{" +
                "sizeArrayList=" + sizeArrayList +
                ", elements=" + Arrays.toString(elements) +
                '}';
    }

    public boolean add(G item) {
        if (sizeArrayList < capacity) {
            elements[sizeArrayList++] = item;
            System.out.println(Arrays.toString(elements));
            return true;
        } else {
            increaseCapacityOfArrayOfElements();
            elements[sizeArrayList++] = item;
            System.out.println(Arrays.toString(elements));
            return true;
        }
    }

    public void add(int index, G item) {
        if (index > sizeArrayList) {
            throw new IndexOutOfBoundsException();
        } else {
            if (index == sizeArrayList) {
                add(item);
            } else {
                if (sizeArrayList < capacity - 1) {
                    insert(index, item);
                    System.out.println(Arrays.toString(elements));
                } else {
                    increaseCapacityOfArrayOfElements();
                    insert(index, item);
                    System.out.println(Arrays.toString(elements));
                }
            }
        }
    }

    public void clear() {
        if (sizeArrayList > 0) {
            while (sizeArrayList > 0) {
                elements[--sizeArrayList] = null;
            }
        }
    }

    public boolean isEmpty() {
        return sizeArrayList == 0;
    }

    public G get(int index) {
        if (index < 0 || index >= sizeArrayList) {
            throw new IndexOutOfBoundsException();
        } else {
            return elements(index);
        }
    }

    public G set(int index, G item) {
        if (index < 0 || index >= sizeArrayList) {
            throw new IndexOutOfBoundsException();
        } else {
            G oldItem = elements(index);
            elements[index] = item;
            return oldItem;
        }
    }

    public int size() {
        return sizeArrayList;
    }

    public boolean contains(G item) {
        if (item == null) {
            for (int i = 0; i < sizeArrayList; i++) {
                if (elements[i] == null) {
                    return true;
                }
            }
            return false;
        } else {
            for (int i = 0; i < sizeArrayList; i++) {
                if (item.equals(elements[i])) {
                    return true;
                }
            }
            return false;
        }
    }

    public int indexOf(G item) {
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

    public int lastIndexOf(G item) {
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
            System.arraycopy(elements, index + 1, copyElementsAfterIndex, 0, sizeArrayList + 1 - index);
            System.arraycopy(copyElementsAfterIndex, 0, elements, index, copyElementsAfterIndex.length);
            elements[sizeArrayList] = null;
            sizeArrayList--;
            System.out.println(Arrays.toString(elements));
            return deletedItem;
        }
    }

    public boolean remove(G item) {
        int indexItem = indexOf(item);
        if (indexItem != -1) {
            remove(indexItem);
            return true;
        }
        return false;
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
    private G elements(int index) {
        return (G) elements[index];
    }

    private void increaseCapacityOfArrayOfElements() {
        Object[] copyElements = new Object[elements.length];
        System.arraycopy(elements, 0, copyElements, 0, elements.length);
        capacity += DEFAULT_CAPACITY;
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