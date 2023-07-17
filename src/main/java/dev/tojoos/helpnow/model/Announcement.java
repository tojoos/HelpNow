package dev.tojoos.helpnow.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.*;

/**
 * Class representing the charity announcements.
 *
 * @author Jan Olszówka
 * @version 1.0
 * @since 2022-11-06
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Announcement extends BaseEntity {
  private String title;

  @Column(columnDefinition = "LONGTEXT")
  private String description;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "author_id")
  @JsonIgnoreProperties("createdAnnouncements")
  private User author;

  private String status;
  private LocalDateTime creationDateTime;
}
