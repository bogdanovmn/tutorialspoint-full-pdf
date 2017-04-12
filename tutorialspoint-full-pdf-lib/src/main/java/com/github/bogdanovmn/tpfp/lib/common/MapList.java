package com.github.bogdanovmn.tpfp.lib.common;

import java.util.List;

/**
 * По сути это обертка над Map&lt;KeyType, List&lt;ListType>>
 */
public interface MapList<KeyType, ListType> {
    /**
     * Аналогично Map.put(), но занчение добавляется в список
     * Таким образом, все добавленные значения будут доступны
     *
     * @param key ключ для Map
     * @param value значение, которое будет добавлено в Map по ключу (в конец соотв. списка)
     */
    void put(KeyType key, ListType value);

    /**
     * Аналогично Map.get()
     *
     * @param key ключ для Map
     * @return список всех значеений по указанному ключу
     */
    List<ListType> get(KeyType key);
    /**
     *
     */
}
