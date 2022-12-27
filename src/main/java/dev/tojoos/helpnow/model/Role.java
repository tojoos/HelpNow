package dev.tojoos.helpnow.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Role extends BaseEntity {
}
