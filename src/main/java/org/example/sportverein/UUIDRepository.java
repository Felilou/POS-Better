package org.example.sportverein;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface UUIDRepository<E extends AbstractEntity> extends JpaRepository<E, Long> {

    @Query("select e from #{#entityName} e where e.uuid = :uuid")
    E findByUUID(UUID uuid);

}