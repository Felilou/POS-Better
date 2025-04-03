package org.example.sportverein;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public abstract class AbstractService<E extends AbstractEntity> {

    public List<E> getAll() {
        return getUUIDRepository().findAll();
    }

    public E getByUUID(UUID uuid) {
        return getUUIDRepository().findByUUID(uuid);
    }

    @Transactional
    public E create(CreateDTO<E> dto) {
        E e = dto.toEntity();
        return getUUIDRepository().save(e);
    }

    @Transactional
    public E updateEntity(UpdateDTO<E> dto, UUID uuid) {
        E entity = getByUUID(uuid);
        entity = dto.updateEntity(entity);
        return getUUIDRepository().save(entity);
    }

    @Transactional
    public E delete(UUID uuid) {
        E entity = getByUUID(uuid);
        getUUIDRepository().delete(entity);
        return entity;
    }

    public abstract UUIDRepository<E> getUUIDRepository();

}
