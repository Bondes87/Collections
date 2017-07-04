package com.shpp.dbondarenko;

/**
 * File: BDSHashMap.java
 * The class that implements the hash map structure.
 * Created by Dmitro Bondarenko on 26.06.2017.
 */
public class BDSHashMap<K, V> {
    /**
     * The constant that corresponds to the initial length of the array for storing the items of the hash map.
     */
    private static final int DEFAULT_CAPACITY = 16;
    /**
     * Hash map size.
     */
    private int sizeHashMap;
    /**
     * An array for storing the entries of the hash map.
     */
    private Entry<K, V>[] table;

    /**
     * Create an empty hash map.
     */
    public BDSHashMap() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Create an empty hash map with a specified capacity.
     *
     * @param capacity The initial capacity for this hash map.
     */
    public BDSHashMap(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("The capacity can not be less than 0.");
        } else {
            initTable(capacity);
        }
    }

    /**
     * Create a hash map containing the elements in the specified of hash map.
     *
     * @param bdsHashMap The hash map whose elements are to be placed into this hash map.
     */
    public BDSHashMap(BDSHashMap<? extends K, ? extends V> bdsHashMap) {
        this(DEFAULT_CAPACITY);
        putAll(bdsHashMap);
    }

    /**
     * Return a string representation of the contents of the specified hash map.
     *
     * @return A string representation of hash map.
     */
    @Override
    public String toString() {
        BDSArrayList<Entry<K, V>> bdsArrayList = new BDSArrayList<>();
        for (Entry<K, V> entry : table) {
            if (entry != null) {
                do {
                    bdsArrayList.add(entry);
                } while ((entry = entry.getNext()) != null);
            }
        }
        return bdsArrayList.toString();
    }

    /**
     * Return a shallow copy of this BDSHashMap instance.
     *
     * @return A clone of this BDSHashMap instance.
     */
    public Object clone() {
        BDSHashMap<K, V> bdsHashMap;
        if (sizeHashMap < DEFAULT_CAPACITY) {
            bdsHashMap = new BDSHashMap<>(DEFAULT_CAPACITY);
        } else {
            bdsHashMap = new BDSHashMap<>(sizeHashMap);
        }
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

    /**
     * Associate the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value is replaced.
     *
     * @param key   The key with which the specified value is to be associated.
     * @param value The value to be associated with the specified key.
     * @return The previous value associated with key, or null if there was no mapping for key.
     * (A null return can also indicate that the map previously associated null with key.).
     */
    public V put(K key, V value) {
        if (key == null) {
            return putForNullKey(value);
        } else {
            return putForNotNullKey(key, value);
        }
    }

    /**
     * Return the number of key-value mappings in this map.
     *
     * @return The number of key-value mappings in this map.
     */
    public int size() {
        return sizeHashMap;
    }

    /**
     * Remove all of the mappings from this map. The map will be empty after this call returns.
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        if (sizeHashMap > 0) {
            sizeHashMap = 0;
            table = new Entry[table.length];
        }
    }

    /**
     * Return true if this map contains no key-value mappings.
     *
     * @return The true if this map contains no key-value mappings.
     */
    public boolean isEmpty() {
        return sizeHashMap == 0;
    }

    /**
     * Return true if this map contains a mapping for the specified key.
     *
     * @param key The key whose presence in this map is to be tested.
     * @return The true if this map contains a mapping for the specified key.
     */
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

    /**
     * Return true if this map maps one or more keys to the specified value.
     *
     * @param value The value whose presence in this map is to be tested.
     * @return The true if this map maps one or more keys to the specified value.
     */
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

    /**
     * Return the value to which the specified key is mapped,
     * or null if this map contains no mapping for the key.
     *
     * @param key The key whose associated value is to be returned.
     * @return The value to which the specified key is mapped,
     * or null if this map contains no mapping for the key
     */
    public V get(Object key) {
        return getOrDefault(key, null);
    }

    /**
     * Return the value to which the specified key is mapped,
     * or defaultValue if this map contains no mapping for the key.
     *
     * @param key          The key whose associated value is to be returned.
     * @param defaultValue The default mapping of the key.
     * @return The value to which the specified key is mapped,
     * or defaultValue if this map contains no mapping for the key
     */
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

    /**
     * Copy all of the mappings from the specified map to this map.
     * These mappings will replace any mappings that this map had for any
     * of the keys currently in the specified map.
     *
     * @param bdsHashMap The mappings to be stored in this map.
     */
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

    /**
     * Remove the mapping for the specified key from this map if present.
     *
     * @param key The key whose mapping is to be removed from the map.
     * @return The previous value associated with key, or null if there was no mapping for key.
     * (A null return can also indicate that the map previously associated null with key.)
     */
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

    /**
     * Remove the entry for the specified key only if it is currently mapped to the specified value.
     *
     * @param key   The key with which the specified value is associated.
     * @param value The value expected to be associated with the specified key.
     * @return True if the value was removed.
     */
    public boolean remove(Object key, Object value) {
        int hash = hash(key);
        int index;
        if (key != null) {
            return removeForNotNullKey(key, value, hash);
        } else {
            return removeForNullKey(value, hash);
        }
    }

    /**
     * Replaces the entry for the specified key only if currently mapped to the specified value.
     *
     * @param key      The key with which the specified value is associated.
     * @param oldValue The value expected to be associated with the specified key.
     * @param newValue The value to be associated with the specified key.
     * @return True if the value was replaced.
     */
    public boolean replace(K key, V oldValue, V newValue) {
        int hash = hash(key);
        if (key != null) {
            return replaceForNotNullKey(key, oldValue, newValue, hash);
        } else {
            return replaceForNullKey(oldValue, newValue, table[hash]);
        }
    }

    /**
     * Replace the entry for the specified key only if it is currently mapped to some value.
     *
     * @param key   The key with which the specified value is associated.
     * @param value The value to be associated with the specified key.
     * @return The previous value associated with the specified key, or null if there was no mapping for the key.
     * (A null return can also indicate that the map previously associated null with the key,
     * if the implementation supports null values.).
     */
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

    /**
     * If the specified key is not already associated with a value (or is mapped to null)
     * associates it with the given value and returns null, else returns the current value.
     *
     * @param key   The key with which the specified value is to be associated.
     * @param value The value to be associated with the specified key.
     * @return The previous value associated with the specified key, or null if there
     * was no mapping for the key. (A null return can also indicate that the map previously
     * associated null with the key, if the implementation supports null values.)
     */
    public V putIfAbsent(K key, V value) {
        if (key == null) {
            return putIfAbsentForNullKey(value);
        } else {
            return putIfAbsentForNotNullKey(key, value);
        }
    }

    /**
     * Return a BDSArrayList view of the mappings contained in this map.
     *
     * @return A BDSArrayList view of the mappings contained in this map.
     */
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

    /**
     * Returns a BDSArrayList view of the keys contained in this map.
     *
     * @return A BDSArrayList view of the keys contained in this map.
     */
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

    /**
     * Returns a BDSArrayList view of the values contained in this map.
     *
     * @return A BDSArrayList view of the values contained in this map.
     */
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


    /**
     * If the specified key is not already associated with a value (or is mapped to null)
     * associates it with the given value and returns null, else returns the current value.
     * (The key is not null)
     *
     * @param key   The key with which the specified value is to be associated.
     * @param value The value to be associated with the specified key.
     * @return The previous value associated with the specified key, or null if there
     * was no mapping for the key. (A null return can also indicate that the map previously
     * associated null with the key, if the implementation supports null values.)
     */
    private V putIfAbsentForNotNullKey(K key, V value) {
        if (sizeHashMap < table.length) {
            return addEntryIfAbsent(key, value);
        } else {
            increaseSizeOfTable();
            return addEntryIfAbsent(key, value);
        }
    }

    /**
     * If the specified key is not already associated with a value (or is mapped to null)
     * associates it with the given value and returns null, else returns the current value.
     * (The key is not null)
     *
     * @param key   The key with which the specified value is to be associated.
     * @param value The value to be associated with the specified key.
     * @return The previous value associated with the specified key, or null if there
     * was no mapping for the key. (A null return can also indicate that the map previously
     * associated null with the key, if the implementation supports null values.)
     */
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

    /**
     * If the specified key is not already associated with a value (or is mapped to null)
     * associates it with the given value and returns null, else returns the current value.
     * (The key is null)
     *
     * @param value The value to be associated with the specified key.
     * @return The previous value associated with the specified key, or null if there
     * was no mapping for the key. (A null return can also indicate that the map previously
     * associated null with the key, if the implementation supports null values.)
     */
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

    /**
     * Replaces the entry for the specified key only if currently mapped to the specified value.
     * (The key is null)
     *
     * @param oldValue The value expected to be associated with the specified key.
     * @param newValue The value to be associated with the specified key.
     * @param entry    The entry where the null key can be stored.
     * @return True if the value was replaced.
     */
    private boolean replaceForNullKey(V oldValue, V newValue, Entry<K, V> entry) {
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

    /**
     * Replaces the entry for the specified key only if currently mapped to the specified value.
     * (The key is not null)
     *
     * @param key      The key with which the specified value is associated.
     * @param oldValue The value expected to be associated with the specified key.
     * @param newValue The value to be associated with the specified key.
     * @param hash     The hash key code.
     * @return True if the value was replaced.
     */
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

    /**
     * Remove the entry for the specified key only if it is currently mapped to the specified value.
     * (The key is null)
     *
     * @param value The value expected to be associated with the specified key.
     * @param hash  The hash key code.
     * @return True if the value was removed.
     */
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

    /**
     * Remove the entry for the specified key only if it is currently mapped to the specified value.
     * (The key is not null)
     *
     * @param key   The key with which the specified value is associated.
     * @param value The value expected to be associated with the specified key.
     * @param hash  The hash key code.
     * @return True if the value was removed.
     */
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

    /**
     * Remove the mapping for the specified key from this map.
     *
     * @param index The index of the cell of the array where this entry can be found.
     * @param entry The entry that can be deleted.
     * @return The previous value associated with key.(A null return can also indicate
     * that the map previously associated null with key.)
     */
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

    /**
     * Associate the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value is replaced.
     * (The key is null)
     *
     * @param value The value to be associated with the specified key.
     * @return The previous value associated with key, or null if there was no mapping for key.
     * (A null return can also indicate that the map previously associated null with key.).
     */
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

    /**
     * Associate the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value is replaced.
     * (The key is not null)
     *
     * @param key   The key with which the specified value is to be associated.
     * @param value The value to be associated with the specified key.
     * @return The previous value associated with key, or null if there was no mapping for key.
     * (A null return can also indicate that the map previously associated null with key.).
     */
    private V putForNotNullKey(K key, V value) {
        if (sizeHashMap < table.length) {
            return addEntry(key, value, table);
        } else {
            increaseSizeOfTable();
            return addEntry(key, value, table);
        }
    }

    /**
     * Increase the capacity of the array in which the map entries are stored.
     */
    @SuppressWarnings("unchecked")
    private void increaseSizeOfTable() {
        sizeHashMap = 0;
        Entry<K, V>[] newTable = new Entry[table.length * 2];
        for (Entry<K, V> aTable : table) {
            Entry<K, V> entry = aTable;
            if (entry != null) {
                addEntry(entry.getKey(), entry.getValue(), newTable);
                while ((entry = entry.getNext()) != null) {
                    addEntry(entry.getKey(), entry.getValue(), newTable);
                }
            }
        }
        table = newTable;
    }

    /**
     * Add an entry to the array.
     *
     * @param key   The key with which the specified value is to be associated.
     * @param value The value to be associated with the specified key.
     * @param table The array for storing entries.
     * @return The previous value associated with key, or null if there was no mapping for key.
     * (A null return can also indicate that the map previously associated null with key.)
     */
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

    /**
     * Get the index to place an entry in the array.
     *
     * @param hash        The hash key code.
     * @param tableLength The size of the array.
     * @return The index for placing an entry in an array.
     */
    private int getIndexForTable(int hash, int tableLength) {
        return hash & tableLength - 1;
    }

    /**
     * Get the hash key code.
     *
     * @param key The key for which the hash code is defined.
     * @return The hash key code.
     */
    private int hash(Object key) {
        if (key == null) {
            return 0;
        } else {
            return key.hashCode();
        }
    }

    /**
     * Initialize an array.
     *
     * @param capacity The initial capacity of the array.
     */
    @SuppressWarnings("unchecked")
    private void initTable(int capacity) {
        table = new Entry[capacity];
    }
}