package org.example.sportverein.Match;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.example.sportverein.AbstractEntity;
import org.example.sportverein.Team.Team;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
public class Match extends AbstractEntity {

    @ManyToOne
    @NonNull
    private final Team homeTeam;

    @ManyToOne
    @NonNull
    private final Team awayTeam;

    private final LocalDateTime kickOffTime;

    protected Match(@NonNull Team homeTeam, @NonNull Team awayTeam, LocalDateTime kickOffTime) {
        this.awayTeam = awayTeam;
        this.homeTeam = homeTeam;

        if(awayTeam.equals(homeTeam)) {
            throw new IllegalArgumentException("Team cant play against it self");
        }

        this.kickOffTime = kickOffTime;
    }

    @Override
    public void prePersistHook() {

    }

    @Override
    public void preRemoveHook() {

    }

    public boolean contestantsContain(Team team) {
        return homeTeam.equals(team) || awayTeam.equals(team);
    }

}
