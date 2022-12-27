package dev.tojoos.helpnow.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
public class User extends Person {
    @OneToMany (cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("user")
    private List<Announcement> createdAnnouncements = new ArrayList<>();

    public void addCreatedAnnouncement(Announcement announcement) {
        announcement.setAuthor(this);
        this.getCreatedAnnouncements().add(announcement);
    }

    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();
}
