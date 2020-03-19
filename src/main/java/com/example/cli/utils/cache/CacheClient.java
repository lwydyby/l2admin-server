package com.example.cli.utils.cache;

public interface CacheClient<T> {


    T get(String key);

    boolean isExist(String key);

    void set(String key, T value);

    void set(final String key, T value, int millSeconds);

    void remove(String key);
}
