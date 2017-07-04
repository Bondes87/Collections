package com.shpp.dbondarenko;

/**
 * File: Node.java
 * The class that implements the structure of node of a linked list.
 * Created by Dmitro Bondarenko on 28.06.2017.
 */
public class Node<G> {
    /**
     * The item.
     */
    private G item;
    /**
     * The previous node.
     */
    private Node<G> previous;
    /**
     * The next node.
     */
    private Node<G> next;

    /**
     * Create a node.
     */
    public Node(G item, Node<G> previous, Node<G> next) {
        this.item = item;
        this.previous = previous;
        this.next = next;
    }

    /**
     * Return a string representation of the contents of the specified node.
     *
     * @return A string representation of node.
     */
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
