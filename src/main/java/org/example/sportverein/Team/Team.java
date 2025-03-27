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

    @OneToMany
    private final List<Match> matches = new ArrayList<>();

    private String name;

    public void addMatch(Match match) {
        if(!match.contestantsContain(this)) throw new IllegalArgumentException("Team does not play in match");
        matches.add(match);
    }

    public void removeMatch(Match match) {
        matches.remove(match);
    }

    public List<Match> getMatches() {
        return List.copyOf(matches);
    }

    @Override
    public void prePersistHook() {
    }

    @Override
    public void preRemoveHook() {
        throw new UnsupportedOperationException("Cant delete a team");
    }

}
