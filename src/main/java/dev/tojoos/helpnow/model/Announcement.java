package dev.tojoos.helpnow.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @JoinColumn(name="author_id")
    @JsonIgnoreProperties("createdAnnouncements")
    private User author;
    private String status;
    private LocalDateTime creationDateTime;
}
