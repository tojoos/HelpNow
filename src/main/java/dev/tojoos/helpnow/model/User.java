package dev.tojoos.helpnow.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends Person {
    @OneToMany
    private List<Fundraise> supportedFundraises;
    @OneToMany
    private List<Announcement> createdAnnouncements;
}
