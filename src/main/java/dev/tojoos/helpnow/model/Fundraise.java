package dev.tojoos.helpnow.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
    @OneToMany
    private List<Employee> assignedEmployees;
    @ManyToOne
    private Fundraise organization;
    private LocalDate startingDate;
    private LocalDate endingDate;
}
