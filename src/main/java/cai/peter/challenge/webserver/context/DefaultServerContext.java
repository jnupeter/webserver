package cai.peter.challenge.webserver.context;

import cai.peter.challenge.webserver.cache.CacheManager;
import cai.peter.challenge.webserver.cache.DefaultCacheManagerImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap-based default implementation of ServerContext.
 *
 * Created by peter on 18/06/17.
 */
public class DefaultServerContext implements ServerContext{

    private Map<String, Object> context = new HashMap<>();

    public DefaultServerContext() {
        context.put(ServerContext.CACHE_MANAGER_KEY, new DefaultCacheManagerImpl());
    }

    @Override
    public CacheManager getCacheManager() {
        return (CacheManager)context.get(ServerContext.CACHE_MANAGER_KEY);
    }
}
