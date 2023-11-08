package dev.tojoos.helpnow.repositories;

import dev.tojoos.helpnow.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
}
