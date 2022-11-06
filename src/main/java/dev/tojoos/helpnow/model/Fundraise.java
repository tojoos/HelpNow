package dev.tojoos.helpnow.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Fundraise extends BaseEntity {
    private String description;
    private Long requiredAmount;
    private Long raisedAmount;
    @OneToMany
    private List<Employee> assignedEmployees;
    @ManyToMany
    private List<User> supportingUsers;
    @ManyToOne
    private Fundraise organization;
    private LocalDateTime startingDate;
    private LocalDateTime endingDate;
}
