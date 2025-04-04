package org.example.sportverein.persons.player;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.*;
import org.example.sportverein.AbstractEntity;
import org.example.sportverein.team.Team;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

@EqualsAndHashCode(callSuper = true, exclude = "player")
@Entity
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
public class PlayerTeamMembership extends AbstractEntity {

    @ManyToOne
    @NonNull
    private final Player player;

    @ManyToOne
    @Nullable //null means no team
    private final Team team;

    @NonNull
    @Column(name = "from_time")
    private final LocalDateTime from;

    @NonNull
    @Column(name = "to_time")
    private final LocalDateTime to;

    @Transient
    public Period getPeriod() {
        return Period.between(from.toLocalDate(), to.toLocalDate());
    }

    @Transient
    public Long getDaysTotal() {
        return ChronoUnit.DAYS.between(from, to);
    }

    @Override
    public void prePersistHook() {

    }

    @Override
    public void preRemoveHook() {
        throw new UnsupportedOperationException("Cant remove a Player Team Membership");
    }

}
