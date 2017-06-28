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

    @Override
    public String toString() {
        if (sizeLinkedList > 0) {
            StringBuilder list = new StringBuilder("{");
            Node<G> node = first;
            for (int i = 0; i < sizeLinkedList; i++) {
                list.append(node.getItem()).append(", ");
                node = node.getNext();
            }
            list = new StringBuilder(list.substring(0, list.length() - 2) + "}");
            return list.toString();
        }
        return "{}";
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
        System.out.println(sizeLinkedList);
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
        System.out.println(sizeLinkedList);
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
            System.out.println(sizeLinkedList);
        }
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
        G firstItem = peekFirst();
        if (firstItem == null) {
            throw new NoSuchElementException();
        } else {
            return firstItem;
        }
    }

    public G getLast() {
        G lastItem = peekLast();
        if (lastItem == null) {
            throw new NoSuchElementException();
        } else {
            return lastItem;
        }
    }

    public G peek() {
        return peekFirst();
    }

    public G element() {
        return getFirst();
    }
}

