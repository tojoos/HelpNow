package dev.tojoos.helpnow.model;

import javax.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Class storing statistics of the web application.
 *
 * @author Jan Olszówka
 * @version 1.0
 * @since 2022-12-11
 */
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Statistics extends BaseEntity {
  private Long serviceVisits;
  private Long completedFundraises;
  private Long totalFundsRaised;
  private Long peopleHelped;
}
