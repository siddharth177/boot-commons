package io.github.siddharth177.bootcommons.services;

import java.util.List;
import java.util.Optional;

/**
 * A generic service interface that defines standard CRUD (Create, Read, Update, Delete) operations.
 * By implementing this interface, services can provide a consistent API for managing entities,
 * reducing boilerplate code and promoting a standardized service layer.
 *
 * @param <T>  The type of the entity to be managed.
 * @param <ID> The type of the entity's identifier (e.g., Long, String).
 */
public interface IBaseService<T, ID> {

    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
     * entity instance completely.
     *
     * @param entity The entity to save. Must not be {@literal null}.
     * @return The saved entity; will never be {@literal null}.
     */
    T save(T entity);

    /**
     * Saves all given entities.
     *
     * @param entities An {@link Iterable} of entities to save. Must not be {@literal null}.
     * @return The saved entities; will never be {@literal null}.
     * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
     */
    List<T> saveAll(Iterable<T> entities);

    /**
     * Retrieves an entity by its ID.
     *
     * @param id The ID of the entity to retrieve. Must not be {@literal null}.
     * @return An {@link Optional} containing the entity if found, or {@link Optional#empty()} if not.
     */
    Optional<T> findById(ID id);

    /**
     * Checks if an entity with the given ID exists.
     *
     * @param id The ID to check. Must not be {@literal null}.
     * @return {@literal true} if an entity with the given ID exists, {@literal false} otherwise.
     */
    boolean existsById(ID id);

    /**
     * Returns all instances of the type.
     *
     * @return A {@link List} of all entities; will never be {@literal null}.
     */
    List<T> findAll();

    /**
     * Returns all instances of the type {@code T} with the given IDs.
     * <p>
     * If some or all ids are not found, no entities are returned for these IDs.
     * <p>
     * Note that the order of elements in the result is not guaranteed.
     *
     * @param ids an {@link Iterable} of IDs to retrieve. Must not be {@literal null}.
     * @return the found entities; will never be {@literal null}. The size of the {@link List} can be equal to or less than the number of given {@literal ids}.
     * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
     */
    List<T> findAllById(Iterable<ID> ids);

    /**
     * Returns the number of entities available.
     *
     * @return The number of entities.
     */
    long count();

    /**
     * Deletes the entity with the given ID.
     *
     * @param id The ID of the entity to delete. Must not be {@literal null}.
     * @throws org.springframework.dao.EmptyResultDataAccessException if no entity with the given id exists.
     */
    void deleteById(ID id);

    /**
     * Deletes a given entity.
     *
     * @param entity The entity to delete. Must not be {@literal null}.
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    void delete(T entity);

    /**
     * Deletes all instances of the type {@code T} with the given IDs.
     *
     * @param entities The entities to delete. Must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
     */
    void deleteAll(Iterable<? extends T> entities);

    /**
     * Deletes all entities managed by the service.
     */
    void deleteAll();
}
