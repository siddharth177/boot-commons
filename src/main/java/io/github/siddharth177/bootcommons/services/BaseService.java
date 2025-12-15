package io.github.siddharth177.bootcommons.services;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

/**
 * An abstract base class that provides a concrete implementation of the {@link IBaseService} interface.
 * This class uses a {@link JpaRepository} to perform standard CRUD operations, reducing boilerplate code
 * in concrete service implementations.
 *
 * <p>To use this class, extend it and provide a {@link JpaRepository} for the specific entity type.</p>
 *
 * @param <T>  The type of the entity to be managed.
 * @param <ID> The type of the entity's identifier.
 */
public abstract class BaseService<T, ID> implements IBaseService<T, ID> {

    private final JpaRepository<T, ID> repository;

    /**
     * Constructs a new {@code BaseService} with the given repository.
     *
     * @param repository The {@link JpaRepository} to use for data access. Must not be {@literal null}.
     */
    public BaseService(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> saveAll(Iterable<T> entities) {
        return repository.saveAll(entities);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> findAllById(Iterable<ID> ids) {
        return repository.findAllById(ids);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long count() {
        return repository.count();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        repository.deleteAll(entities);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
