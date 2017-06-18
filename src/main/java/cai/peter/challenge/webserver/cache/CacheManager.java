package cai.peter.challenge.webserver.cache;

/**
 * Created by peter on 18/06/17.
 */
public interface CacheManager {
    void setCache(String key, Object content);
    Object getCache(String key);
}
