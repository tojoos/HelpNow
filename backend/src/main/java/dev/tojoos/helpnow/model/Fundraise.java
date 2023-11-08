package dev.tojoos.helpnow.model;

import com.fasterxml.jackson.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Class representing fundraise entity.
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
@Entity
public class Fundraise extends BaseEntity {
  @Column(columnDefinition = "LONGTEXT")
  private String description;
  private Long requiredAmount;
  private Long raisedAmount;
  private String imageUrl;

  @OneToMany
  private List<Employee> assignedEmployees = new ArrayList<>();

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "organization_id")
  @JsonIgnoreProperties("createdFundraises")
  private Organization organization;
  private LocalDate startingDate;
  private LocalDate endingDate;

  public void addAssignedEmployee(Employee employee) {
    employee.setAssignedFundraise(this);
    this.getAssignedEmployees().add(employee);
  }
}
