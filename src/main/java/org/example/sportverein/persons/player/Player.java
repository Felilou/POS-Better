package org.example.sportverein.persons.player;

import jakarta.persistence.*;
import lombok.*;
import org.example.sportverein.team.Team;
import org.example.sportverein.persons.Person;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
public class Player extends Person {

    private boolean archived;

    @ManyToOne
    private Team team;

    private LocalDateTime joinedCurrentTeamAt;

    @OneToMany(cascade = CascadeType.PERSIST)
    private final Set<PlayerTeamMembership> teamMemberships = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Position position;

    public void setTeam(Team team) {
        PlayerTeamMembership membership = new PlayerTeamMembership(this, getTeam(), getJoinedCurrentTeamAt(), LocalDateTime.now());
        teamMemberships.add(membership);

        this.team = team;
        this.joinedCurrentTeamAt = LocalDateTime.now();
    }

    public Set<PlayerTeamMembership> getTeamMemberships() {
        return Set.copyOf(teamMemberships);
    }


    /**
    * This method checks if the player has played for the given Team at the given Time.
    *
    * @param at The time
    * @param team The Team
    */
    @Transient
    public boolean hasPlayedForTeamAtTime(LocalDateTime at, Team team) {

        boolean isCurrentTeam = at.isAfter(getJoinedCurrentTeamAt())&&team.equals(getTeam());

        return !teamMemberships.stream().filter(playerTeamMembership -> {
            boolean teamFits = playerTeamMembership.getTeam()!=null&&playerTeamMembership.getTeam().equals(team);
            boolean periodFits = playerTeamMembership.getFrom().isBefore(at)&&playerTeamMembership.getTo().isAfter(at);
            return teamFits && periodFits;
        }).toList().isEmpty()||isCurrentTeam;
    }

    public enum Position {

        GK("Goalkeeper"),
        CB("Center Back"),
        LB("Left Back"),
        RB("Right Back"),
        SW("Sweeper"),
        DM("Defensive Midfielder"),
        CM("Central Midfielder"),
        AM("Attacking Midfielder"),
        LM("Left Midfielder"),
        RM("Right Midfielder"),
        LW("Left Winger"),
        RW("Right Winger"),
        CF("Center Forward"),
        ST("Striker");

        public final String longName;

        Position(String longName) {
            this.longName = longName;
        }
    }

    @Override
    public void prePersistHook() {
    }

    @Override
    public void preRemoveHook() {
        if(!teamMemberships.isEmpty()||team!=null) {
            throw new UnsupportedOperationException("Cant remove a player which has played for a team or is playing for a team, can only be archived");
        }
    }
}
