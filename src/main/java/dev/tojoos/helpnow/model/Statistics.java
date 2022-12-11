package dev.tojoos.helpnow.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

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
