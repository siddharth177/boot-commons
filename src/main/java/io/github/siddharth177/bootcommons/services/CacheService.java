package io.github.siddharth177.bootcommons.services;

/**
 * A generic service interface for caching operations.
 *
 * @param <K> The type of the cache key.
 * @param <V> The type of the cache value.
 */
public interface CacheService<K, V> {

    /**
     * Saves a value in the cache with the specified key.
     *
     * @param key   The key to associate with the value.
     * @param value The value to be cached.
     * @return The saved value.
     */
    V save(K key, V value);

    /**
     * Retrieves a value from the cache for the specified key.
     *
     * @param key The key of the value to retrieve.
     * @return The cached value, or {@code null} if not found.
     */
    V get(K key);

    /**
     * Removes a value from the cache for the specified key.
     *
     * @param key The key of the value to remove.
     */
    void remove(K key);

    /**
     * Clears all entries from the cache.
     */
    void clear();

    /**
     * Checks if the cache contains a value for the specified key.
     *
     * @param key The key to check.
     * @return {@code true} if the cache contains the key, {@code false} otherwise.
     */
    boolean contains(K key);
}
