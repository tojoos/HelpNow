package dev.tojoos.helpnow.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Organization extends BaseEntity {
    @OneToMany (cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("organization")
    private List<Fundraise> createdFundraises;
    @Column(columnDefinition = "LONGTEXT")
    private String description;
}
