package dev.tojoos.helpnow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Basic mail entity.
 *
 * @author Jan Olszówka
 * @version 1.0
 * @since 2023-01-16
 */
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Mail extends BaseEntity {
  private String recipientEmailAddress;
  private String recipientName;
  private String subject;

  @Column(columnDefinition = "LONGTEXT")
  private String content;
}
