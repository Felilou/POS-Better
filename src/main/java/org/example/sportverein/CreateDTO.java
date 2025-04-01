package org.example.sportverein;

public interface CreateDTO<E extends AbstractEntity> {
    E toEntity();
}