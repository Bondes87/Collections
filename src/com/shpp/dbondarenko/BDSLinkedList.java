package com.shpp.dbondarenko;

import java.util.NoSuchElementException;

/**
 * File: BDSLinkedList.java
 * The class that implements the linked list structure.
 * Created by Dmitro Bondarenko on 26.06.2017.
 */
public class BDSLinkedList<G> {
    /**
     * Linked list size.
     */
    private int sizeLinkedList;
    /**
     * First node of linked list.
     */
    private Node<G> first;
    /**
     * Last node of linked list.
     */
    private Node<G> last;

    /**
     * Create an empty linked list.
     */
    public BDSLinkedList() {
    }

    /**
     * Create a linked list containing the elements in the specified of linked list.
     *
     * @param bdsLinkedList The linked list whose elements are to be placed into this linked list.
     */
    public BDSLinkedList(BDSLinkedList<? extends G> bdsLinkedList) {
        this();
        addAll(bdsLinkedList);
    }

    /**
     * Return a string representation of the contents of the specified linked list.
     *
     * @return A string representation of linked list.
     */
    @Override
    public String toString() {
        if (sizeLinkedList > 0) {
            StringBuilder list = new StringBuilder("[");
            Node<G> node = first;
            for (int i = 0; i < sizeLinkedList; i++) {
                list.append(node.getItem()).append(", ");
                node = node.getNext();
            }
            list = new StringBuilder(list.substring(0, list.length() - 2) + "]");
            return list.toString();
        }
        return "[]";
    }

    /**
     * Return a shallow copy of this BDSLinkedList instance.
     *
     * @return A clone of this BDSLinkedList instance.
     */
    public Object clone() {
        BDSLinkedList<G> newBdsLinkedList = new BDSLinkedList<>();
        Node<G> node = first;
        for (int i = 0; i < sizeLinkedList; i++) {
            newBdsLinkedList.add(node.getItem());
            node = node.getNext();
        }
        return newBdsLinkedList;
    }

    /**
     * Append the specified item to the end of this list.
     *
     * @param item The item to be appended to this list
     * @return True if the operation was successful otherwise false.
     */
    public boolean add(G item) {
        addLast(item);
        return true;
    }

    /**
     * Append the specified element to the end of this list.
     *
     * @param item The item to add.
     */
    public void addLast(G item) {
        if (first == null) {
            first = new Node<>(item, null, last);
        } else if (last == null) {
            last = new Node<>(item, first, null);
            Node<G> next = last;
            first.setNext(next);
        } else {
            Node<G> next = new Node<>(item, last, null);
            last.setNext(next);
            last = next;
        }
        sizeLinkedList++;
    }

    /**
     * Append the specified element to the beginning of this list.
     *
     * @param item The item to add.
     */
    public void addFirst(G item) {
        if (last == null) {
            last = new Node<>(item, first, null);
        } else if (first == null) {
            first = new Node<>(item, null, last);
            Node<G> previous = first;
            last.setPrevious(previous);
        } else {
            Node<G> previous = new Node<>(item, null, first);
            first.setPrevious(previous);
            first = previous;
        }
        sizeLinkedList++;
    }

    /**
     * Insert the specified element at the specified position in this list..
     *
     * @param index The index at which the specified element is to be inserted.
     * @param item  The item to be inserted to this list.
     */
    public void add(int index, G item) {
        if (index < 0 || index >= sizeLinkedList) {
            throw new IndexOutOfBoundsException();
        } else {
            Node<G> oldNodeByIndex = first;
            for (int i = 0; i < index; i++) {
                oldNodeByIndex = oldNodeByIndex.getNext();
            }
            Node<G> previousNodeByIndex = oldNodeByIndex.getPrevious();
            Node<G> newNodeByIndex = new Node<>(item, previousNodeByIndex, oldNodeByIndex);
            previousNodeByIndex.setNext(newNodeByIndex);
            oldNodeByIndex.setPrevious(newNodeByIndex);
            sizeLinkedList++;
        }
    }

    /**
     * Insert all of the items in the specified linked list into this list, starting at the specified position.
     *
     * @param index         The index at which to insert the first item from the specified linked list.
     * @param bdsLinkedList The linked list containing items to be added to this list.
     * @return True if this list changed as a result of the call.
     */
    public boolean addAll(int index, BDSLinkedList<? extends G> bdsLinkedList) {
        if (index < 0 || index > sizeLinkedList) {
            throw new IndexOutOfBoundsException();
        } else if (bdsLinkedList.isEmpty()) {
            throw new NullPointerException();
        } else {
            if (index == 0) {
                addAllToBegin(bdsLinkedList);
            } else if (index == sizeLinkedList) {
                addAllToEnd(bdsLinkedList);
            } else {
                addAllToMiddle(index, bdsLinkedList);
            }
            return true;
        }
    }

    /**
     * Append all of the items in the specified collection to the end of this list.
     *
     * @param bdsLinkedList The linked list containing items to be added to this list.
     * @return True if this list changed as a result of the call.
     */
    public boolean addAll(BDSLinkedList<? extends G> bdsLinkedList) {
        return addAll(sizeLinkedList, bdsLinkedList);
    }

    /**
     * Return the item at the specified position in this list.
     *
     * @param index The index of the item to return.
     * @return The item at the specified position in this list.
     */
    public G get(int index) {
        if (index < 0 || index >= sizeLinkedList) {
            throw new IndexOutOfBoundsException();
        } else {
            Node<G> nodeByIndex = first;
            for (int i = 0; i < index; i++) {
                nodeByIndex = nodeByIndex.getNext();
            }
            return nodeByIndex.getItem();
        }
    }

    /**
     * Retrieve, but does not remove, the first item of this list,
     * or returns null if this list is empty.
     *
     * @return The first item of this list, or null if this list is empty.
     */
    public G peekFirst() {
        if (sizeLinkedList == 0) {
            return null;
        } else if (first == null) {
            return last.getItem();
        } else {
            return first.getItem();
        }
    }

    /**
     * Retrieve, but does not remove, the last item of this list,
     * or returns null if this list is empty.
     *
     * @return The last item item of this list, or null if this list is empty.
     */
    public G peekLast() {
        if (sizeLinkedList == 0) {
            return null;
        } else if (last == null) {
            return first.getItem();
        } else {
            return last.getItem();
        }
    }

    /**
     * Return the first item in this list.
     *
     * @return The first item of this list.
     */
    public G getFirst() {
        if (sizeLinkedList == 0) {
            throw new NoSuchElementException();
        } else {
            return peekFirst();
        }
    }

    /**
     * Return the last item in this list.
     *
     * @return The last item of this list.
     */
    public G getLast() {
        if (sizeLinkedList == 0) {
            throw new NoSuchElementException();
        } else {
            return peekLast();
        }
    }

    /**
     * Retrieve, but does not remove, the head (first item) of this list.
     *
     * @return The head item of this list, or null if this list is empty.
     */
    public G peek() {
        return peekFirst();
    }

    /**
     * Retrieve, but does not remove, the head (first item) of this list.
     *
     * @return The head item of this list.
     */
    public G element() {
        return getFirst();
    }

    /**
     * Return the number of items in this list.
     *
     * @return The number of items in this list.
     */
    public int size() {
        return sizeLinkedList;
    }

    /**
     * Retrieve and remove the head (first item) of this list.
     *
     * @return The head of this list, or null if this list is empty.
     */
    public G pool() {
        return poolFirst();
    }

    /**
     * Retrieve and remove the first item of this list, or return null if this list is empty.
     *
     * @return The first item of this list, or null if this list is empty.
     */
    public G poolFirst() {
        Node<G> head;
        if (sizeLinkedList == 0) {
            return null;
        } else if (first == null) {
            head = last;
            last = null;
            sizeLinkedList--;
            return head.getItem();
        } else {
            head = first;
            first = head.getNext();
            sizeLinkedList--;
            return head.getItem();
        }
    }

    /**
     * Retrieve and remove the last item of this list, or return null if this list is empty.
     *
     * @return The last item of this list, or null if this list is empty.
     */
    public G poolLast() {
        Node<G> tail;
        if (sizeLinkedList == 0) {
            return null;
        } else if (last == null) {
            tail = first;
            first = null;
            sizeLinkedList--;
            return tail.getItem();
        } else {
            tail = last;
            last = tail.getPrevious();
            sizeLinkedList--;
            return tail.getItem();
        }
    }

    /**
     * Removes and returns the first item from this list.
     *
     * @return The first item of this list, or null if this list is empty.
     */
    public G removeFirst() {
        if (sizeLinkedList == 0) {
            throw new NoSuchElementException();
        } else {
            return poolFirst();
        }
    }

    /**
     * Removes and returns the last item from this list.
     *
     * @return The last item of this list, or null if this list is empty.
     */
    public G removeLast() {
        if (sizeLinkedList == 0) {
            throw new NoSuchElementException();
        } else {
            return poolLast();
        }
    }

    /**
     * Retrieve and remove the head (first item) of this list.
     *
     * @return The head of this list.
     */
    public G remove() {
        return removeFirst();
    }

    /**
     * Remove all of the items from this list.
     */
    public void clear() {
        if (sizeLinkedList > 0) {
            first = null;
            last = null;
            sizeLinkedList = 0;
        }
    }

    /**
     * Insert the specified item at the front of this list.
     *
     * @param item The item to insert.
     * @return True if the operation was successful.
     */
    public boolean offerFirst(G item) {
        addFirst(item);
        return true;
    }

    /**
     * Insert the specified item at the end of this list.
     *
     * @param item The item to insert.
     * @return True if the operation was successful.
     */
    public boolean offerLast(G item) {
        addLast(item);
        return true;
    }

    /**
     * Add the specified item as the tail (last item) of this list.
     *
     * @param item The item to add.
     * @return True if the operation was successful.
     */
    public boolean offer(G item) {
        return add(item);
    }

    /**
     * Insert the item at the front of this list.
     *
     * @param item The item to push.
     */
    public void push(G item) {
        addFirst(item);
    }

    /**
     * Removes and return the first item of this list.
     *
     * @return The the item at the front of this list.
     */
    public G pop() {
        return removeFirst();
    }

    /**
     * Replace the item at the specified position in this list with the specified item.
     *
     * @param index   The index of the item to replace.
     * @param newItem The item to be stored at the specified position.
     * @return The item previously at the specified position.
     */
    public G set(int index, G newItem) {
        if (index < 0 || index >= sizeLinkedList) {
            throw new IndexOutOfBoundsException();
        } else {
            G previousItem;
            Node<G> nodeByIndex = first;
            for (int i = 0; i < index; i++) {
                nodeByIndex = nodeByIndex.getNext();
            }
            previousItem = nodeByIndex.getItem();
            nodeByIndex.setItem(newItem);
            return previousItem;
        }
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
        Node<G> nodeByIndex = first;
        if (item == null) {
            if (nodeByIndex.getItem() == null) {
                return 0;
            }
            for (int i = 1; i < sizeLinkedList; i++) {
                nodeByIndex = nodeByIndex.getNext();
                if (nodeByIndex.getItem() == null) {
                    return i;
                }
            }
        } else {
            if (nodeByIndex.getItem().equals(item)) {
                return 0;
            }
            for (int i = 1; i < sizeLinkedList; i++) {
                nodeByIndex = nodeByIndex.getNext();
                if (nodeByIndex.getItem().equals(item)) {
                    return i;
                }
            }
        }
        return -1;
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
        int index = -1;
        Node<G> nodeByIndex = first;
        if (item == null) {
            if (nodeByIndex.getItem() == null) {
                index = 0;
            }
            for (int i = 1; i < sizeLinkedList; i++) {
                nodeByIndex = nodeByIndex.getNext();
                if (nodeByIndex.getItem() == null) {
                    index = i;
                }
            }
        } else {
            if (nodeByIndex.getItem().equals(item)) {
                index = 0;
            }
            for (int i = 1; i < sizeLinkedList; i++) {
                nodeByIndex = nodeByIndex.getNext();
                if (nodeByIndex.getItem().equals(item)) {
                    index = i;
                }
            }
        }
        return index;
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
     * Remove the item at the specified position in this list.
     *
     * @param index The index of the item to be removed.
     * @return The item that was removed from the list.
     */
    public G remove(int index) {
        if (index < 0 || index >= sizeLinkedList) {
            throw new IndexOutOfBoundsException();
        } else {
            Node<G> nodeByIndex = first;
            if (index == 0) {
                return removeFirst();
            } else if (index == sizeLinkedList - 1) {
                return removeLast();
            } else {
                for (int i = 0; i < index; i++) {
                    nodeByIndex = nodeByIndex.getNext();
                }
                Node<G> previousNodeByIndex = nodeByIndex.getPrevious();
                Node<G> nextNodeByIndex = nodeByIndex.getNext();
                previousNodeByIndex.setNext(nextNodeByIndex);
                nextNodeByIndex.setPrevious(previousNodeByIndex);
                sizeLinkedList--;
                return nodeByIndex.getItem();
            }
        }
    }

    /**
     * Remove the first occurrence of the specified item from this list, if it is present.
     *
     * @param item The item to be removed from this list, if present.
     * @return True if this list contained the specified item.
     */
    public boolean remove(Object item) {
        return removeFirstOccurrence(item);
    }

    /**
     * Remove the first occurrence of the specified item in this list
     * (when traversing the list from head to tail).
     * If the list does not contain the item, it is unchanged.
     *
     * @param item The item to be removed from this list, if present.
     * @return True if this list contained the specified item.
     */
    public boolean removeFirstOccurrence(Object item) {
        int index = indexOf(item);
        if (index == -1) {
            return false;
        } else {
            remove(index);
            return true;
        }
    }

    /**
     * Remove the last occurrence of the specified item in this list
     * (when traversing the list from head to tail).
     * If the list does not contain the item, it is unchanged.
     *
     * @param item The item to be removed from this list, if present.
     * @return True if this list contained the specified item.
     */
    public boolean removeLastOccurrence(Object item) {
        int index = lastIndexOf(item);
        if (index == -1) {
            return false;
        } else {
            remove(index);
            return true;
        }
    }

    /**
     * Return an array containing all of the items in this list in proper sequence
     * (from first to last element).
     *
     * @return An array containing all of the items in this list in proper sequence.
     **/
    public Object[] toArray() {
        Object[] elements = new Object[sizeLinkedList];
        Node<G> nodeByIndex = first;
        for (int i = 0; i < sizeLinkedList; i++) {
            elements[i] = nodeByIndex.getItem();
            nodeByIndex = nodeByIndex.getNext();
        }
        return elements;
    }

    /**
     * Return true if this list contains no items.
     *
     * @return True if this list contains no items.
     */
    public boolean isEmpty() {
        return sizeLinkedList == 0;
    }

    /**
     * Insert all of the items in the specified linked list into this list, starting at the specified position
     * (The index is not the first or the last).
     *
     * @param index         The index at which to insert the first item from the specified linked list.
     * @param bdsLinkedList The linked list containing items to be added to this list.
     */
    private void addAllToMiddle(int index, BDSLinkedList<? extends G> bdsLinkedList) {
        Object[] items = bdsLinkedList.toArray();
        Node<G> nodeByIndex = first;
        Node<G> previousNodeByIndex;
        Node<G> newNodeByIndex;
        for (int i = 0; i < index; i++) {
            nodeByIndex = nodeByIndex.getNext();
        }
        previousNodeByIndex = nodeByIndex.getPrevious();
        for (int i = 0; i < bdsLinkedList.size(); i++) {
            @SuppressWarnings("unchecked") G item = (G) items[i];
            if (i == bdsLinkedList.size() - 1) {
                newNodeByIndex = new Node<>(item, previousNodeByIndex, nodeByIndex);
                if (previousNodeByIndex != null) {
                    previousNodeByIndex.setNext(newNodeByIndex);
                }
                nodeByIndex.setPrevious(newNodeByIndex);
            } else {
                newNodeByIndex = new Node<>(item, previousNodeByIndex, null);
                if (previousNodeByIndex != null) {
                    previousNodeByIndex.setNext(newNodeByIndex);
                }
                previousNodeByIndex = newNodeByIndex;
            }
            sizeLinkedList++;
        }
    }

    /**
     * Insert all of the items in the specified linked list into end of this list.
     *
     * @param bdsLinkedList The linked list containing items to be added to this list.
     */
    private void addAllToEnd(BDSLinkedList<? extends G> bdsLinkedList) {
        Object[] items = bdsLinkedList.toArray();
        Node<G> previousNodeByIndex = last;
        Node<G> newNodeByIndex;
        for (int i = 0; i < bdsLinkedList.size(); i++) {
            @SuppressWarnings("unchecked") G item = (G) items[i];
            newNodeByIndex = new Node<>(item, previousNodeByIndex, null);
            previousNodeByIndex.setNext(newNodeByIndex);
            previousNodeByIndex = newNodeByIndex;
            sizeLinkedList++;
        }
    }

    /**
     * Insert all of the items in the specified linked list into beginning of this list.
     *
     * @param bdsLinkedList The linked list containing items to be added to this list.
     */
    private void addAllToBegin(BDSLinkedList<? extends G> bdsLinkedList) {
        Object[] items = bdsLinkedList.toArray();
        Node<G> nodeByIndex = null;
        Node<G> previousNodeByIndex = null;
        Node<G> newNodeByIndex;
        for (int i = 0; i < bdsLinkedList.size(); i++) {
            @SuppressWarnings("unchecked") G item = (G) items[i];
            if (i == bdsLinkedList.size() - 1 && bdsLinkedList.size() != 1) {
                newNodeByIndex = new Node<>(item, previousNodeByIndex, nodeByIndex);
                if (previousNodeByIndex != null) {
                    previousNodeByIndex.setNext(newNodeByIndex);
                }
                if (nodeByIndex != null) {
                    nodeByIndex.setPrevious(newNodeByIndex);
                }
                last = newNodeByIndex;
            } else if (i == 0) {
                nodeByIndex = first;
                newNodeByIndex = new Node<>(item, null, null);
                first = newNodeByIndex;
                previousNodeByIndex = newNodeByIndex;
            } else {
                newNodeByIndex = new Node<>(item, previousNodeByIndex, null);
                if (previousNodeByIndex != null) {
                    previousNodeByIndex.setNext(newNodeByIndex);
                }
                previousNodeByIndex = newNodeByIndex;
            }
            sizeLinkedList++;
        }
    }
}


