package com.github.bogdanovmn.tpfp.lib.common;

import java.util.*;

/**
 * Реализация интерфейса {@link MapList}
 * В качестве списка используется ArrayList
 * В качестве Map используется HashMap
 */
public class LinkedHashMapArrayList<KeyType, ListType> implements MapList<KeyType, ListType> {
    private final Map<KeyType, List<ListType>> data = new LinkedHashMap<>();

    @Override
    public void put(KeyType key, ListType value) {
        List<ListType> values = this.data.computeIfAbsent(key, k -> new ArrayList<>());

        values.add(value);
    }

    @Override
    public List<ListType> get(KeyType key) {
        return this.data.get(key);
    }

    public Set<KeyType> keys() {
        return this.data.keySet();
    }
}
