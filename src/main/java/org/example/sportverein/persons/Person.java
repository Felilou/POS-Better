package org.example.sportverein.persons;

import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.example.sportverein.AbstractEntity;

import java.time.Duration;
import java.time.LocalDate;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Person extends AbstractEntity {

    @NonNull
    String firstName;

    @NonNull
    String lastName;

    @NonNull
    LocalDate birthDate;

    @Embedded
    PhoneNumber phoneNumber;

    @Email
    String email;

    @Transient
    public Duration getAge(){
        return Duration.between(birthDate, LocalDate.now());
    }

}
