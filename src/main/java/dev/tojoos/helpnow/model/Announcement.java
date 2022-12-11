package dev.tojoos.helpnow.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Announcement extends BaseEntity {
    private String title;
    private String description;
    @ManyToOne
    private User user;
}
