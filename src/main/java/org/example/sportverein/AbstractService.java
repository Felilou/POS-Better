package org.example.sportverein;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public abstract class AbstractService<E extends AbstractEntity> {

    public List<E> getAll() {
        return getUUIDRepository().findAll();
    }

    public E getByUUID(UUID uuid) {
        return getUUIDRepository().findByUUID(uuid);
    }

    public E create(CreateDTO<E> dto) {
        E e = dto.toEntity();
        return getUUIDRepository().save(e);
    }


    public E updateEntity(UpdateDTO<E> dto, UUID uuid) {
        E entity = getByUUID(uuid);
        dto.updateEntity(entity);
        return entity;
    }

    public E delete(UUID uuid) {
        E entity = getByUUID(uuid);
        getUUIDRepository().delete(entity);
        return entity;
    }

    public abstract UUIDRepository<E> getUUIDRepository();

}
