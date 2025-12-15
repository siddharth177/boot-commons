package io.github.siddharth177.bootcommons.controller;

import io.github.siddharth177.bootcommons.services.IBaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * A generic REST controller that provides standard CRUD (Create, Read, Update, Delete) endpoints
 * for a given entity type. This controller is designed to work with a service that implements the
 * {@link IBaseService} interface, allowing for rapid development of RESTful APIs with minimal boilerplate code.
 *
 * <p>To use this controller, extend it and provide a concrete {@link IBaseService} implementation
 * for the specific entity and its ID type.</p>
 *
 * @param <T>  The type of the entity to be managed.
 * @param <ID> The type of the entity's identifier (e.g., Long, String).
 */
@RestController
public abstract class GenericRestController<T, ID> {

    private final IBaseService<T, ID> service;

    /**
     * Constructs a new {@code GenericRestController} with the given service.
     *
     * @param service The {@link IBaseService} to use for handling business logic.
     */
    public GenericRestController(IBaseService<T, ID> service) {
        this.service = service;
    }

    /**
     * Creates a new entity.
     *
     * @param entity The entity to create, passed in the request body.
     * @return A {@link ResponseEntity} containing the created entity and an HTTP status of {@code 201 (Created)}.
     */
    @PostMapping
    public ResponseEntity<T> create(@RequestBody T entity) {
        T createdEntity = service.save(entity);
        return new ResponseEntity<>(createdEntity, HttpStatus.CREATED);
    }

    /**
     * Retrieves all entities.
     *
     * @return A {@link ResponseEntity} containing a list of all entities and an HTTP status of {@code 200 (OK)}.
     */
    @GetMapping
    public ResponseEntity<List<T>> getAll() {
        List<T> entities = service.findAll();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    /**
     * Retrieves an entity by its ID.
     *
     * @param id The ID of the entity to retrieve.
     * @return A {@link ResponseEntity} containing the found entity if it exists, with an HTTP status of {@code 200 (OK)},
     * or an HTTP status of {@code 404 (Not Found)} if the entity does not exist.
     */
    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable ID id) {
        Optional<T> entity = service.findById(id);
        return entity.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Updates an existing entity.
     *
     * @param id     The ID of the entity to update.
     * @param entity The updated entity data, passed in the request body.
     * @return A {@link ResponseEntity} containing the updated entity and an HTTP status of {@code 200 (OK)}.
     * If the entity does not exist, it returns an HTTP status of {@code 404 (Not Found)}.
     */
    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable ID id, @RequestBody T entity) {
        if (!service.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        T updatedEntity = service.save(entity);
        return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
    }

    /**
     * Deletes an entity by its ID.
     *
     * @param id The ID of the entity to delete.
     * @return A {@link ResponseEntity} with an HTTP status of {@code 204 (No Content)} if the deletion is successful,
     * or an HTTP status of {@code 404 (Not Found)} if the entity does not exist.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        if (!service.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
