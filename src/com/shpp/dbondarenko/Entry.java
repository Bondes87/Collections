package com.shpp.dbondarenko;

/**
 * File: Entry.java
 * The class that implements the structure of entry of a hash map.
 * Created by Dmitro Bondarenko on 02.07.2017.
 */
public class Entry<K, V> {
    /**
     * The hash code of key.
     */
    private int hash;
    /**
     * The key.
     */
    private K key;
    /**
     * The value.
     */
    private V value;
    /**
     * The next entry.
     */
    private Entry<K, V> next;

    /**
     * Create a entry.
     */
    public Entry(int hash, K key, V value, Entry<K, V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    /**
     * Return a string representation of the contents of the specified entry.
     *
     * @return A string representation of entry.
     */
    @Override
    public String toString() {
        return "{" + key + ", " + value + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry<?, ?> entry = (Entry<?, ?>) o;
        if (hash != entry.hash) return false;
        if (key != null ? !key.equals(entry.key) : entry.key != null) return false;
        return value != null ? value.equals(entry.value) : entry.value == null;
    }

    public int getHash() {
        return hash;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Entry<K, V> getNext() {
        return next;
    }
}
