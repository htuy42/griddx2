package com.htuy.gridgame.collections;

import java.util.HashMap;
import java.util.function.Function;

public class CacheMap<K, V> extends HashMap<K, V> {

    private final Function<? super K, ? extends V> provider;

    public CacheMap(Function<K, V> provider) {
        this.provider = provider;
    }

    public V retrieve(K key) {
        return super.computeIfAbsent(key, provider);
    }
}
