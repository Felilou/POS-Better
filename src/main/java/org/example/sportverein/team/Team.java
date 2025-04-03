package org.example.sportverein.team;


import jakarta.persistence.Entity;
import lombok.*;
import org.example.sportverein.AbstractEntity;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team extends AbstractEntity {

    private boolean archived = false;

    private String name;

    @Override
    public void prePersistHook() {
    }

    @Override
    public void preRemoveHook() {
        throw new UnsupportedOperationException("Cant delete a team");
    }

}
