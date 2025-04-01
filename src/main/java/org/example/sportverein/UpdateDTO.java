package org.example.sportverein;

public interface UpdateDTO<E extends AbstractEntity> {
    E updateEntity(E entity);
}
