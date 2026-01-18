package me.papi.cache;

import java.util.concurrent.*;
import java.util.*;

public class PlaceholderCache {
    private static final Map<String, CacheEntry> cache = new ConcurrentHashMap<>();

    public static String get(String key, Callable<String> supplier) {
        CacheEntry e = cache.get(key);
        if (e != null && !e.expired()) return e.value;
        try {
            String v = supplier.call();
            cache.put(key, new CacheEntry(v, System.currentTimeMillis() + 5000));
            return v;
        } catch (Exception ex) {
            return "error";
        }
    }

    record CacheEntry(String value, long expire) {
        boolean expired() { return System.currentTimeMillis() > expire; }
    }
}
