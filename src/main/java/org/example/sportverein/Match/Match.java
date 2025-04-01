package org.example.sportverein.Match;

import jakarta.persistence.*;
import lombok.*;
import org.example.sportverein.AbstractEntity;
import org.example.sportverein.Team.Team;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private final List<MatchEvent> events = new ArrayList<>();

    protected Match(@NonNull Team homeTeam, @NonNull Team awayTeam, LocalDateTime kickOffTime) {
        this.awayTeam = awayTeam;
        this.homeTeam = homeTeam;

        if(awayTeam.equals(homeTeam)) {
            throw new IllegalArgumentException("Team cant play against it self");
        }

        this.kickOffTime = kickOffTime;
    }

    public void addEvent(MatchEvent event) {
        events.add(event);
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
