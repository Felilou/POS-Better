package org.example.sportverein.match;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.example.sportverein.AbstractEntity;
import org.example.sportverein.persons.player.Player;

@Entity
@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
public class MatchEvent extends AbstractEntity {

    @ManyToOne
    private Player player;

    @ManyToOne
    private Match match;

    @Setter
    @NonNull
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Min(0)
    @Max(90)
    @Column(name = "min")
    @Setter
    private int minute;

    public enum EventType {

        GOAL("Goal"),
        ASSIST("Assist"),
        OWN_GOAL("Own Goal"),
        YELLOW_CARD("Yellow Card"),
        RED_CARD("Red Card"),
        INJURY("Injury"),
        FOUL_COMMITTED("Foul Committed"),
        FOUL_SUFFERED("Foul Suffered"),
        SHOT_ON_TARGET("Shot on Target"),
        SHOT_OFF_TARGET("Shot off Target"),
        HIT_WOODWORK("Hit Woodwork");

        public final String description;

        EventType(String description) {
            this.description = description;
        }
    }

    protected MatchEvent(@NonNull Match match) {
        setMatch(match);
    }

    public void setMatch(@NonNull Match match) {
        if(this.match != null) {
            throw new IllegalStateException("Match has already been set");
        }

        this.match = match;
        match.addEvent(this);
    }

    protected void setPlayer(@NonNull Player player) {
        if(!player.hasPlayedForTeamAtTime(match.getKickOffTime(), match.getAwayTeam())&&
                !player.hasPlayedForTeamAtTime(match.getKickOffTime(), getMatch().getHomeTeam()))
            throw new IllegalArgumentException("Players Team History doesnt Macht");

        this.player = player;
    }

    @Override
    public void prePersistHook() {
    }

    @Override
    public void preRemoveHook() {
    }
}
