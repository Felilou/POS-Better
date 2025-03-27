package org.example.sportverein.persons;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.example.sportverein.Team.Team;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Staff extends Person {

    @ManyToOne
    private Team team;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        HEAD_COACH("Head Coach"),
        ASSISTANT_COACH("Assistant Coach"),
        GOALKEEPER_COACH("Goalkeeper Coach"),
        FITNESS_COACH("Fitness Coach"),
        PHYSIOTHERAPIST("Physiotherapist"),
        TEAM_DOCTOR("Team Doctor"),
        PERFORMANCE_ANALYST("Performance Analyst"),
        SCOUT("Scout"),
        NUTRITIONIST("Nutritionist"),
        SPORTS_PSYCHOLOGIST("Sports Psychologist"),
        EQUIPMENT_MANAGER("Equipment Manager"),
        TEAM_MANAGER("Team Manager"),
        TECHNICAL_DIRECTOR("Technical Director"),
        YOUTH_DEVELOPMENT_COACH("Youth Development Coach"),
        REHABILITATION_SPECIALIST("Rehabilitation Specialist");

        public final String title;

        Role(String title) {
            this.title = title;
        }

    }

    @Override
    public void prePersistHook() {

    }

    @Override
    public void preRemoveHook() {

    }
}
