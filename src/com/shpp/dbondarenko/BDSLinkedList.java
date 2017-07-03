package com.shpp.dbondarenko;

import java.util.NoSuchElementException;

/**
 * File: BDSLinkedList.java
 * Created by Dmitro Bondarenko on 26.06.2017.
 */
public class BDSLinkedList<G> {
    private int sizeLinkedList;
    private Node<G> first;
    private Node<G> last;

    public BDSLinkedList() {
    }

    public BDSLinkedList(BDSLinkedList<? extends G> bdsLinkedList) {
        this();
        addAll(bdsLinkedList);
    }

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

    public Object clone() {
        BDSLinkedList<G> newBdsLinkedList = new BDSLinkedList<>();
        Node<G> node = first;
        for (int i = 0; i < sizeLinkedList; i++) {
            newBdsLinkedList.add(node.getItem());
            node = node.getNext();
        }
        return newBdsLinkedList;
    }

    public boolean add(G item) {
        addLast(item);
        return true;
    }

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

    public boolean addAll(BDSLinkedList<? extends G> bdsLinkedList) {
        return addAll(sizeLinkedList, bdsLinkedList);
    }

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

    public G peekFirst() {
        if (sizeLinkedList == 0) {
            return null;
        } else if (first == null) {
            return last.getItem();
        } else {
            return first.getItem();
        }
    }

    public G peekLast() {
        if (sizeLinkedList == 0) {
            return null;
        } else if (last == null) {
            return first.getItem();
        } else {
            return last.getItem();
        }
    }

    public G getFirst() {
        if (sizeLinkedList == 0) {
            throw new NoSuchElementException();
        } else {
            return peekFirst();
        }
    }

    public G getLast() {
        if (sizeLinkedList == 0) {
            throw new NoSuchElementException();
        } else {
            return peekLast();
        }
    }

    public G peek() {
        return peekFirst();
    }

    public G element() {
        return getFirst();
    }

    public int size() {
        return sizeLinkedList;
    }

    public G pool() {
        return poolFirst();
    }

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

    public G removeFirst() {
        if (sizeLinkedList == 0) {
            throw new NoSuchElementException();
        } else {
            return poolFirst();
        }
    }

    public G removeLast() {
        if (sizeLinkedList == 0) {
            throw new NoSuchElementException();
        } else {
            return poolLast();
        }
    }

    public G remove() {
        return removeFirst();
    }

    public void clear() {
        if (sizeLinkedList > 0) {
            first = null;
            last = null;
            sizeLinkedList = 0;
        }
    }

    public boolean offerFirst(G item) {
        addFirst(item);
        return true;
    }

    public boolean offerLast(G item) {
        addLast(item);
        return true;
    }

    public boolean offer(G item) {
        return add(item);
    }

    public void push(G item) {
        addFirst(item);
    }

    public G pop() {
        return removeFirst();
    }

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

    public int indexOf(Object o) {
        Node<G> nodeByIndex = first;
        if (o == null) {
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
            if (nodeByIndex.getItem().equals(o)) {
                return 0;
            }
            for (int i = 1; i < sizeLinkedList; i++) {
                nodeByIndex = nodeByIndex.getNext();
                if (nodeByIndex.getItem().equals(o)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        int index = -1;
        Node<G> nodeByIndex = first;
        if (o == null) {
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
            if (nodeByIndex.getItem().equals(o)) {
                index = 0;
            }
            for (int i = 1; i < sizeLinkedList; i++) {
                nodeByIndex = nodeByIndex.getNext();
                if (nodeByIndex.getItem().equals(o)) {
                    index = i;
                }
            }
        }
        return index;
    }

    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

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

    public boolean remove(Object o) {
        return removeFirstOccurrence(o);
    }

    public boolean removeFirstOccurrence(Object o) {
        int index = indexOf(o);
        if (index == -1) {
            return false;
        } else {
            remove(index);
            return true;
        }
    }

    public boolean removeLastOccurrence(Object o) {
        int index = lastIndexOf(o);
        if (index == -1) {
            return false;
        } else {
            remove(index);
            return true;
        }
    }

    public Object[] toArray() {
        Object[] elements = new Object[sizeLinkedList];
        Node<G> nodeByIndex = first;
        for (int i = 0; i < sizeLinkedList; i++) {
            elements[i] = nodeByIndex.getItem();
            nodeByIndex = nodeByIndex.getNext();
        }
        return elements;
    }

    public boolean isEmpty() {
        return sizeLinkedList == 0;
    }

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


