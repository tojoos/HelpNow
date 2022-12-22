package dev.tojoos.helpnow.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@SuperBuilder
public abstract class Person extends BaseEntity {
    protected String lastName;
    protected String email;
    protected String phone;
}
