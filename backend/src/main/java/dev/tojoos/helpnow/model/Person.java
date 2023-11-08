package dev.tojoos.helpnow.model;

import javax.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Abstract class representing person.
 *
 * @author Jan Olszówka
 * @version 1.0
 * @since 2022-10-31
 */
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
  protected String imageUrl;
}
