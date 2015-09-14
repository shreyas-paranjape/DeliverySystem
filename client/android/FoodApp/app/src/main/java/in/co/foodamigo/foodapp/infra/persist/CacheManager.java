package in.co.foodamigo.foodapp.infra.persist;

import android.util.LruCache;

public class CacheManager {

    private static final LruCache<String, Object> cache = new LruCache<>(10);

    public static void put(String key, Object value) {
        cache.put(key, value);
    }

    public static Object get(String key) {
        return cache.get(key);
    }
}
