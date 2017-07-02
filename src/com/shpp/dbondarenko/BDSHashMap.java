package com.shpp.dbondarenko;

import java.util.Arrays;

/**
 * File: BDSHashMap.java
 * Created by Dmitro Bondarenko on 26.06.2017.
 */
public class BDSHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private int sizeHashMap;
    private Entry<K, V>[] table;

    public BDSHashMap() {
        this(DEFAULT_CAPACITY);
    }

    public BDSHashMap(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("The capacity can not be less than 0.");
        } else {
            initTable(capacity);
        }
    }

    @Override
    public String toString() {
        return "BDSHashMap{" +
                "sizeHashMap=" + sizeHashMap +
                ", table=" + Arrays.toString(table) +
                '}';
    }

    public V put(K key, V value) {
        if (key == null) {
            return putForNullKey(key, value);
        } else {
            return putForOtherKey(key, value);
        }
    }

    public int size() {
        return sizeHashMap;
    }

    @SuppressWarnings("unchecked")
    public void clear() {
        if (sizeHashMap > 0) {
            sizeHashMap = 0;
            table = new Entry[table.length];
        }
    }

    public boolean isEmpty() {
        return sizeHashMap == 0;
    }

    public boolean containsKey(Object key) {
        int hash = hash(key);
        int index;
        if (key != null) {
            index = getIndexForTable(hash, table.length);
            Entry<K, V> entry = table[index];
            if (entry != null) {
                if (key.equals(entry.getKey())) {
                    return true;
                }
                while ((entry = entry.getNext()) != null) {
                    if (key.equals(entry.getKey())) {
                        return true;
                    }
                }
                return false;
            } else {
                return false;
            }
        } else {
            index = hash;
            Entry<K, V> entry = table[index];
            if (entry != null) {
                if (null == entry.getKey()) {
                    return true;
                }
                while ((entry = entry.getNext()) != null) {
                    if (null == entry.getKey()) {
                        return true;
                    }
                }
                return false;
            } else {
                return false;
            }
        }
    }

    public boolean containsValue(Object value) {
        if (value != null) {
            for (int i = 0; i < table.length; i++) {
                Entry<K, V> entry = table[i];
                if (entry != null) {
                    if (value.equals(entry.getValue())) {
                        return true;
                    }
                    while ((entry = entry.getNext()) != null) {
                        if (value.equals(entry.getValue())) {
                            return true;
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < table.length; i++) {
                Entry<K, V> entry = table[i];
                if (entry != null) {
                    if (entry.getValue() == null) {
                        return true;
                    }
                    while ((entry = entry.getNext()) != null) {
                        if (entry.getValue() == null) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private V putForNullKey(K key, V value) {
        int hash = hash(key);
        int index = 0;
        if (table[index] == null) {
            table[index] = new Entry<>(hash, key, value, null);
            sizeHashMap++;
            return null;
        } else {
            Entry<K, V> entryByIndex = table[index];
            if (hash == entryByIndex.getHash() && key == entryByIndex.getKey()) {
                V valueEntryByIndex = entryByIndex.getValue();
                entryByIndex.setValue(value);
                return valueEntryByIndex;
            } else {
                Entry<K, V> newEntry = new Entry<>(hash, key, value, entryByIndex);
                table[index] = newEntry;
                sizeHashMap++;
                return value;
            }
        }
    }

    private V putForOtherKey(K key, V value) {
        if (sizeHashMap < table.length) {
            return addEntry(key, value, table);
        } else {
            increaseSizeOfTable();
            return addEntry(key, value, table);
        }
    }

    @SuppressWarnings("unchecked")
    private void increaseSizeOfTable() {
        sizeHashMap = 0;
        Entry<K, V>[] newTable = new Entry[table.length * 2];
        for (int i = 0; i < table.length; i++) {
            Entry<K, V> entry = table[i];
            if (entry != null) {
                addEntry(entry.getKey(), entry.getValue(), newTable);
                while ((entry = entry.getNext()) != null) {
                    addEntry(entry.getKey(), entry.getValue(), newTable);
                }
            }
        }
        table = newTable;
    }

    private V addEntry(K key, V value, Entry<K, V>[] table) {
        int hash = hash(key);
        int index = getIndexForTable(hash, table.length);
        if (table[index] == null) {
            table[index] = new Entry<>(hash, key, value, null);
            sizeHashMap++;
            return null;
        } else {
            Entry<K, V> entryByIndex = table[index];
            if (hash == entryByIndex.getHash() && key.equals(entryByIndex.getKey())) {
                V valueEntryByIndex = entryByIndex.getValue();
                entryByIndex.setValue(value);
                return valueEntryByIndex;
            } else {
                Entry<K, V> newEntry = new Entry<>(hash, key, value, entryByIndex);
                table[index] = newEntry;
                sizeHashMap++;
                return value;
            }
        }
    }


    private int getIndexForTable(int hash, int tableLength) {
        return hash & tableLength - 1;
    }

    private int hash(Object key) {
        if (key == null) {
            return 0;
        } else {
            return key.hashCode();
        }
    }


    @SuppressWarnings("unchecked")
    private void initTable(int capacity) {
        table = new Entry[capacity];
    }

}