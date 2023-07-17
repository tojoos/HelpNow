package dev.tojoos.helpnow.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Class representing users of the application.
 *
 * @author Jan Olszówka
 * @version 1.0
 * @since 2022-10-31
 */
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
  private String username;
  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  private List<Role> roles = new ArrayList<>();

  public void addCreatedAnnouncement(Announcement announcement) {
    announcement.setAuthor(this);
    this.getCreatedAnnouncements().add(announcement);
  }
}
