package dev.tojoos.helpnow.model;

import java.io.Serializable;
import javax.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Base entity class.
 *
 * @author Jan Olszówka
 * @version 1.0
 * @since 2022-10-31
 */
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, updatable = false)
  protected Long id;
  protected String name;
}
