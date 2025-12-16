package io.github.siddharth177.bootcommons.entities;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * An abstract base class for entities that require auditing of creation and modification information.
 * This class includes fields for tracking the creation and last modification timestamps, as well as the
 * users who performed these actions.
 *
 * @param <U> The type of the user identifier.
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> {

    /**
     * Default constructor for {@code Auditable}.
     */
    public Auditable() {
        // Default constructor
    }

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private U createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private U updatedBy;
}
