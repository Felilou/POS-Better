package org.example.sportverein.match;

import jakarta.persistence.*;
import lombok.*;
import org.example.sportverein.AbstractEntity;
import org.example.sportverein.team.Team;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
public class Match extends AbstractEntity {

    @ManyToOne
    private Team homeTeam;

    @ManyToOne
    private Team awayTeam;

    private LocalDateTime kickOffTime;

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

    public void setKickOffTime(LocalDateTime kickOffTime) {
        if (this.kickOffTime != null) {
            throw new IllegalStateException("KickOffTime already set");
        }
        this.kickOffTime = kickOffTime;
    }

    public void setHomeTeam(Team homeTeam) {
        if(this.homeTeam!=null) {
            throw new IllegalStateException("Team already set");
        }
        if(homeTeam.equals(this.awayTeam)) {
            throw new IllegalArgumentException("Team cant play against it self");
        }
        this.homeTeam = homeTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        if(this.awayTeam!=null) {
            throw new IllegalStateException("Team already set");
        }
        if(awayTeam.equals(this.homeTeam)) {
            throw new IllegalArgumentException("Team cant play against it self");
        }
        this.awayTeam = awayTeam;
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

    public List<MatchEvent> getEvents() {
        return List.copyOf(events);
    }

}
