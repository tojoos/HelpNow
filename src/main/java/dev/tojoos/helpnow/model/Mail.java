package dev.tojoos.helpnow.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

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
