package com.shpp.dbondarenko;

/**
 * File: Node.java
 * Created by Dmitro Bondarenko on 28.06.2017.
 */
public class Node<G> {
    private G item;
    private Node<G> previous;
    private Node<G> next;

    public Node(G item, Node<G> previous, Node<G> next) {
        this.item = item;
        this.previous = previous;
        this.next = next;
    }

    @Override
    public String toString() {
        return item.toString();
    }

    public G getItem() {
        return item;
    }

    public void setItem(G item) {
        this.item = item;
    }

    public Node<G> getNext() {
        return next;
    }

    public void setNext(Node<G> next) {
        this.next = next;
    }

    public Node<G> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<G> previous) {
        this.previous = previous;
    }
}
