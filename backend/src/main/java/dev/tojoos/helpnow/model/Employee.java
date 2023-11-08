package dev.tojoos.helpnow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Class representing the employee.
 *
 * @author Jan Olszówka
 * @version 1.0
 * @since 2022-10-31
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
public class Employee extends Person {
  @Column(nullable = false, updatable = false)
  private String employeeCode;
  private String jobTitle;
  private Long salary;

  @ManyToOne
  private Fundraise assignedFundraise;
}
