package org.example.sportverein;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import lombok.Getter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Objects;
import java.util.UUID;

@Getter
@MappedSuperclass
public abstract class AbstractEntity extends AbstractPersistable<Long> {

    private UUID uuid;

    @PrePersist
    private void prePersist() {
        if(uuid == null) {
            uuid = UUID.randomUUID();
        }
        prePersistHook();
    }

    /**
     * this method is executed @PrePersist after a UUID has been set in the entity
     */
    public abstract void prePersistHook();

    @PreRemove
    private void preRemove() {
        this.uuid = null;
        preRemoveHook();
    }

    /**
     * this method is executed @PreRemove after the uuid has been set to null
     */
    public abstract void preRemoveHook();

    @Override
    public boolean equals(Object o) {
        if (getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;

        if(this.isNew() != that.isNew()) return false;

        if(!(this.isNew()&&this.isNew()))
            return super.equals(that);

        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), uuid);
    }

}
