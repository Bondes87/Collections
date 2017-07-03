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

    public BDSHashMap(BDSHashMap<? extends K, ? extends V> bdsHashMap) {
        this(DEFAULT_CAPACITY);
        putAll(bdsHashMap);
    }

    @Override
    public String toString() {
        return "BDSHashMap{" +
                "sizeHashMap=" + sizeHashMap +
                ", table=" + Arrays.toString(table) +
                '}';
    }

    public Object clone() {
        BDSHashMap<K, V> bdsHashMap = new BDSHashMap<>(sizeHashMap);
        for (Entry<K, V> aTable : table) {
            Entry<K, V> entry = aTable;
            if (entry != null) {
                do {
                    bdsHashMap.put(entry.getKey(), entry.getValue());
                } while ((entry = entry.getNext()) != null);
            }
        }
        return bdsHashMap;
    }

    public V put(K key, V value) {
        if (key == null) {
            return putForNullKey(value);
        } else {
            return putForNotNullKey(key, value);
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
                do {
                    if (key.equals(entry.getKey())) {
                        return true;
                    }
                } while ((entry = entry.getNext()) != null);
                return false;
            } else {
                return false;
            }
        } else {
            index = hash;
            Entry<K, V> entry = table[index];
            if (entry != null) {
                do {
                    if (null == entry.getKey()) {
                        return true;
                    }
                } while ((entry = entry.getNext()) != null);
                return false;
            } else {
                return false;
            }
        }
    }

    public boolean containsValue(Object value) {
        if (value != null) {
            for (Entry<K, V> aTable : table) {
                Entry<K, V> entry = aTable;
                if (entry != null) {
                    do {
                        if (value.equals(entry.getValue())) {
                            return true;
                        }
                    } while ((entry = entry.getNext()) != null);
                }
            }
        } else {
            for (Entry<K, V> aTable : table) {
                Entry<K, V> entry = aTable;
                if (entry != null) {
                    do {
                        if (entry.getValue() == null) {
                            return true;
                        }
                    } while ((entry = entry.getNext()) != null);
                }
            }
        }
        return false;
    }

    public V get(Object key) {
        return getOrDefault(key, null);
    }

    public V getOrDefault(Object key, V defaultValue) {
        int hash = hash(key);
        int index;
        if (key != null) {
            index = getIndexForTable(hash, table.length);
            Entry<K, V> entry = table[index];
            if (entry != null) {
                do {
                    if (key.equals(entry.getKey())) {
                        return entry.getValue();
                    }
                } while ((entry = entry.getNext()) != null);
                return defaultValue;
            } else {
                return defaultValue;
            }
        } else {
            index = hash;
            Entry<K, V> entry = table[index];
            if (entry != null) {
                do {
                    if (null == entry.getKey()) {
                        return entry.getValue();
                    }
                } while ((entry = entry.getNext()) != null);
                return defaultValue;
            } else {
                return defaultValue;
            }
        }
    }

    public void putAll(BDSHashMap<? extends K, ? extends V> bdsHashMap) {
        if (bdsHashMap.isEmpty()) {
            throw new NullPointerException();
        } else {
            BDSArrayList<? extends Entry<? extends K, ? extends V>> bdsArrayList = bdsHashMap.entryBDSArrayList();
            for (int i = 0; i < bdsArrayList.size(); i++) {
                @SuppressWarnings("unchecked") Entry<K, V> entry = (Entry<K, V>) bdsArrayList.get(i);
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    public V remove(Object key) {
        int hash = hash(key);
        int index;
        if (key != null) {
            index = getIndexForTable(hash, table.length);
            Entry<K, V> entry = table[index];
            if (entry != null) {
                do {
                    if (key.equals(entry.getKey())) {
                        return removeEntry(index, entry);
                    }
                } while ((entry = entry.getNext()) != null);
                return null;
            } else {
                return null;
            }
        } else {
            index = hash;
            Entry<K, V> entry = table[index];
            if (entry != null) {
                do {
                    if (null == entry.getKey()) {
                        return removeEntry(index, entry);
                    }
                } while ((entry = entry.getNext()) != null);
                return null;
            } else {
                return null;
            }
        }
    }

    public boolean remove(Object key, Object value) {
        int hash = hash(key);
        int index;
        if (key != null) {
            return removeForNotNullKey(key, value, hash);
        } else {
            return removeForNullKey(value, hash);
        }
    }

    public boolean replace(K key, V oldValue, V newValue) {
        int hash = hash(key);
        int index;
        if (key != null) {
            return replaceForNotNullKey(key, oldValue, newValue, hash);
        } else {
            return replaceForNullKey(oldValue, newValue, table[hash]);
        }
    }

    public V replace(K key, V value) {
        int hash = hash(key);
        int index;
        if (key != null) {
            index = getIndexForTable(hash, table.length);
            Entry<K, V> entry = table[index];
            if (entry != null) {
                do {
                    if (key.equals(entry.getKey())) {
                        V oldValue = entry.getValue();
                        entry.setValue(value);
                        return oldValue;
                    }
                } while ((entry = entry.getNext()) != null);
                return null;
            } else {
                return null;
            }
        } else {
            index = hash;
            Entry<K, V> entry = table[index];
            if (entry != null) {
                do {
                    if (null == entry.getKey()) {
                        V oldValue = entry.getValue();
                        entry.setValue(value);
                        return oldValue;
                    }
                } while ((entry = entry.getNext()) != null);
                return null;
            } else {
                return null;
            }
        }
    }

    public V putIfAbsent(K key, V value) {
        if (key == null) {
            return putIfAbsentForNullKey(value);
        } else {
            return putIfAbsentForNotNullKey(key, value);
        }
    }

    public BDSArrayList<Entry<K, V>> entryBDSArrayList() {
        BDSArrayList<Entry<K, V>> bdsArrayList = new BDSArrayList<>();
        for (Entry<K, V> entry : table) {
            if (entry != null) {
                do {
                    bdsArrayList.add(entry);
                } while ((entry = entry.getNext()) != null);
            }
        }
        return bdsArrayList;
    }

    public BDSArrayList<K> keyBDSArrayList() {
        BDSArrayList<K> bdsArrayList = new BDSArrayList<>();
        for (Entry<K, V> entry : table) {
            if (entry != null) {
                do {
                    bdsArrayList.add(entry.getKey());
                } while ((entry = entry.getNext()) != null);
            }
        }
        return bdsArrayList;
    }

    public BDSArrayList<V> values() {
        BDSArrayList<V> bdsArrayList = new BDSArrayList<>();
        for (Entry<K, V> entry : table) {
            if (entry != null) {
                do {
                    bdsArrayList.add(entry.getValue());
                } while ((entry = entry.getNext()) != null);
            }
        }
        return bdsArrayList;
    }

    private V putIfAbsentForNotNullKey(K key, V value) {
        if (sizeHashMap < table.length) {
            return addEntryIfAbsent(key, value);
        } else {
            increaseSizeOfTable();
            return addEntryIfAbsent(key, value);
        }
    }

    private V addEntryIfAbsent(K key, V value) {
        int hash = hash(key);
        int index = getIndexForTable(hash, table.length);
        if (table[index] == null) {
            table[index] = new Entry<>(hash, key, value, null);
            sizeHashMap++;
            return null;
        } else {
            Entry<K, V> entryByIndex = table[index];
            if (hash == entryByIndex.getHash() && key.equals(entryByIndex.getKey())) {
                return entryByIndex.getValue();
            } else {
                Entry<K, V> newEntry = new Entry<>(hash, key, value, entryByIndex);
                table[index] = newEntry;
                sizeHashMap++;
                return null;
            }
        }
    }

    private V putIfAbsentForNullKey(V value) {
        int hash = hash(null);
        int index = 0;
        if (table[index] == null) {
            table[index] = new Entry<>(hash, null, value, null);
            sizeHashMap++;
            return null;
        } else {
            Entry<K, V> entryByIndex = table[index];
            if (hash == entryByIndex.getHash() && null == entryByIndex.getKey()) {
                return entryByIndex.getValue();
            } else {
                Entry<K, V> newEntry = new Entry<>(hash, null, value, entryByIndex);
                table[index] = newEntry;
                sizeHashMap++;
                return null;
            }
        }
    }

    private boolean replaceForNullKey(V oldValue, V newValue, Entry<K, V> kvEntry) {
        Entry<K, V> entry = kvEntry;
        if (entry != null) {
            if (oldValue != null) {
                do {
                    if (null == entry.getKey() && oldValue.equals(entry.getValue())) {
                        entry.setValue(newValue);
                        return true;
                    }
                } while ((entry = entry.getNext()) != null);
            } else {
                do {
                    if (null == entry.getKey() && null == entry.getValue()) {
                        entry.setValue(newValue);
                        return true;
                    }
                } while ((entry = entry.getNext()) != null);
            }
            return false;
        } else {
            return false;
        }
    }

    private boolean replaceForNotNullKey(K key, V oldValue, V newValue, int hash) {
        int index;
        index = getIndexForTable(hash, table.length);
        Entry<K, V> entry = table[index];
        if (entry != null) {
            if (oldValue != null) {
                do {
                    if (key.equals(entry.getKey()) && oldValue.equals(entry.getValue())) {
                        entry.setValue(newValue);
                        return true;
                    }
                } while ((entry = entry.getNext()) != null);
            } else {
                do {
                    if (key.equals(entry.getKey()) && null == entry.getValue()) {
                        entry.setValue(newValue);
                        return true;
                    }
                } while ((entry = entry.getNext()) != null);
            }
            return false;
        } else {
            return false;
        }
    }

    private boolean removeForNullKey(Object value, int hash) {
        Entry<K, V> entry = table[hash];
        if (entry != null) {
            if (value != null) {
                do {
                    if (null == entry.getKey() && value.equals(entry.getValue())) {
                        removeEntry(hash, entry);
                        return true;
                    }
                } while ((entry = entry.getNext()) != null);
            } else {
                do {
                    if (null == entry.getKey() && null == entry.getValue()) {
                        removeEntry(hash, entry);
                        return true;
                    }
                } while ((entry = entry.getNext()) != null);
            }
            return false;
        } else {
            return false;
        }
    }

    private boolean removeForNotNullKey(Object key, Object value, int hash) {
        int index = getIndexForTable(hash, table.length);
        Entry<K, V> entry = table[index];
        if (entry != null) {
            if (value != null) {
                do {
                    if (key.equals(entry.getKey()) && value.equals(entry.getValue())) {
                        removeEntry(index, entry);
                        return true;
                    }
                } while ((entry = entry.getNext()) != null);
            } else {
                do {
                    if (key.equals(entry.getKey()) && null == entry.getValue()) {
                        removeEntry(index, entry);
                        return true;
                    }
                } while ((entry = entry.getNext()) != null);
            }
            return false;
        } else {
            return false;
        }
    }

    private V removeEntry(int index, Entry<K, V> entry) {
        Entry<K, V> nextEntry = entry.getNext();
        if (nextEntry == null) {
            V value = entry.getValue();
            table[index] = null;
            sizeHashMap--;
            return value;
        } else {
            V value = entry.getValue();
            table[index] = nextEntry;
            sizeHashMap--;
            return value;
        }
    }

    private V putForNullKey(V value) {
        int hash = hash(null);
        int index = 0;
        if (table[index] == null) {
            table[index] = new Entry<>(hash, null, value, null);
            sizeHashMap++;
            return null;
        } else {
            Entry<K, V> entryByIndex = table[index];
            if (hash == entryByIndex.getHash() && null == entryByIndex.getKey()) {
                V valueEntryByIndex = entryByIndex.getValue();
                entryByIndex.setValue(value);
                return valueEntryByIndex;
            } else {
                Entry<K, V> newEntry = new Entry<>(hash, null, value, entryByIndex);
                table[index] = newEntry;
                sizeHashMap++;
                return value;
            }
        }
    }

    private V putForNotNullKey(K key, V value) {
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