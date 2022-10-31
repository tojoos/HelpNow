package dev.tojoos.helpnow.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee extends Person {
    @Column(nullable = false, updatable = false)
    private String employeeCode;
    private String jobTitle;
    private Long salary;
    @ManyToOne
    private Fundraise assignedFundraises;
}
