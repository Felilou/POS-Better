package org.example.sportverein.Match;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.example.sportverein.AbstractEntity;
import org.example.sportverein.Team.Team;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.example.sportverein.Match.MatchEvent.EventType;

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

    @OneToMany(cascade = CascadeType.ALL)
    private final Set<MatchEvent> events = new HashSet<>();

    private final LocalDateTime kickOffTime;

    protected Match(@NonNull Team homeTeam, @NonNull Team awayTeam, LocalDateTime kickOffTime) {
        this.awayTeam = awayTeam;
        this.homeTeam = homeTeam;

        if(awayTeam.equals(homeTeam)) {
            throw new IllegalArgumentException("Team cant play against it self");
        }

        this.kickOffTime = kickOffTime;

        awayTeam.addMatch(this);
        homeTeam.addMatch(this);
    }

    @Override
    public void prePersistHook() {

    }

    protected void addEvent(MatchEvent event) {
        events.add(event);
    }

    protected void removeEvent(MatchEvent event) {
        events.remove(event);
    }

    @Override
    public void preRemoveHook() {
        homeTeam.removeMatch(this);
        awayTeam.removeMatch(this);
    }

    public boolean contestantsContain(Team team) {
        return homeTeam.equals(team) || awayTeam.equals(team);
    }

    public Set<MatchEvent> getEvents() {
        return Set.copyOf(events);
    }

    public Set<MatchEvent> getEventsByType(EventType type) {
        return Set.copyOf(events.stream().filter(matchEvent -> matchEvent.getEventType().equals(type)).toList());
    }

}
