package cai.peter.challenge.webserver.cache;

import java.util.HashMap;

/**
 * This is a FAKE implementation for cache manager, will always MISS.
 * Created by peter on 18/06/17.
 */
public class DefaultCacheManagerImpl implements CacheManager {
    private HashMap<String, Object> cacheStorage = new HashMap<>();

    public DefaultCacheManagerImpl() {

    }

    private boolean exists(final String key) {
        return false; //always MISS
    }

    @Override
    public void setCache(final String key, final Object content) {
        cacheStorage.put(key, content);
    }

    @Override
    public Object getCache(String key) {
        if (exists(key)) {
            return cacheStorage.get(key);
        } else {
            return null;
        }
    }
}
