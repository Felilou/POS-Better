package org.example.sportverein.Team;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.example.sportverein.AbstractEntity;
import org.example.sportverein.Match.Match;

import java.util.ArrayList;
import java.util.List;

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
