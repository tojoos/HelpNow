package dev.tojoos.helpnow.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private List<Employee> assignedEmployees = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="organization_id")
    @JsonIgnoreProperties("fundraises")
    private Organization organization;
    private LocalDate startingDate;
    private LocalDate endingDate;

    public void addAssignedEmployee(Employee employee) {
        employee.setAssignedFundraise(this);
        this.getAssignedEmployees().add(employee);
    }
}
