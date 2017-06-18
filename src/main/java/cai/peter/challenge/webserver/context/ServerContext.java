package cai.peter.challenge.webserver.context;

import cai.peter.challenge.webserver.cache.CacheManager;

/**
 * ServerContext will contain reference to the implementaions for the critical components for the server, such as
 * CacheManger (fake implementation is provided), ResponseBuilder (not implemented in this challenge),
 * SessionManager (not implemented in this challenge) etc.
 *
 * Created by peter on 18/06/17.
 */
public interface ServerContext {
    String CACHE_MANAGER_KEY = "context.cachemanager";

    CacheManager getCacheManager();
}
