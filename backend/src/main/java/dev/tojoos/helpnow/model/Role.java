package dev.tojoos.helpnow.model;

import javax.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Class representing role of the user.
 *
 * @author Jan Olszówka
 * @version 1.0
 * @since 2022-12-11
 */
@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Role extends BaseEntity {
}
