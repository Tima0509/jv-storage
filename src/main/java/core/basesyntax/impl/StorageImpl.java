package core.basesyntax.impl;

import core.basesyntax.Storage;

public class StorageImpl<K, V> implements Storage<K, V> {
    private static final int MAX_SIZE = 10;
    private K[] keys;
    private V[] values;
    private int currentSize;

    public StorageImpl() {
        keys = (K[]) new Object[MAX_SIZE];
        values = (V[]) new Object[MAX_SIZE];
        currentSize = 0;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            for (int i = 0; i < currentSize; i++) {
                if (keys[i] == null) {
                    values[i] = value;
                    return;
                }
            }
            if (currentSize < MAX_SIZE) {
                keys[currentSize] = null;
                values[currentSize] = value;
                currentSize++;
            } else {
                throw new RuntimeException("Storage is full");
            }
        } else {
            for (int i = 0; i < currentSize; i++) {
                if (key.equals(keys[i])) {
                    values[i] = value;
                    return;
                }
            }
            if (currentSize < MAX_SIZE) {
                keys[currentSize] = key;
                values[currentSize] = value;
                currentSize++;
            } else {
                throw new RuntimeException("Storage is full");
            }
        }
    }

    @Override
    public V get(K key) {
        if (key == null) {
            for (int i = 0; i < currentSize; i++) {
                if (keys[i] == null) {
                    return values[i];
                }
            }
        } else {
            for (int i = 0; i < currentSize; i++) {
                if (key.equals(keys[i])) {
                    return values[i];
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return currentSize;
    }
}
