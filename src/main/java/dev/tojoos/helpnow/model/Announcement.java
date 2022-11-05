package dev.tojoos.helpnow.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Announcement extends BaseEntity {
    private String title;
    private String description;
    @ManyToOne
    private User user;
}
