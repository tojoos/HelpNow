package dev.tojoos.helpnow.services;

import dev.tojoos.helpnow.model.Announcement;
import dev.tojoos.helpnow.repositories.AnnouncementRepository;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service implementation used for management of the announcement entities.
 *
 * @author Jan Olszówka
 * @version 1.0
 * @since 2022-11-05
 */
@Service
public class AnnouncementServiceImpl implements AnnouncementService {
  private final UserService userService;
  private final AnnouncementRepository announcementRepository;

  public AnnouncementServiceImpl(AnnouncementRepository announcementRepository, UserService userService) {
    this.announcementRepository = announcementRepository;
    this.userService = userService;
  }

  public Announcement add(Announcement announcement) {
    if (announcement.getCreationDateTime() == null) {
      announcement.setCreationDateTime(LocalDateTime.now());
    }
    if (announcement.getStatus() == null) {
      announcement.setStatus("open");
    }

    if (announcement.getAuthor() != null) {
      announcement.setAuthor(userService.getByUsername(announcement.getAuthor().getUsername()));
    } else {
      throw new UsernameNotFoundException("User not found in announcement with title " + announcement.getTitle());
    }
    return announcementRepository.save(announcement);
  }

  public Announcement getById(Long id) {
    return announcementRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Announcement with id=" + id + " not found."));
  }

  public List<Announcement> getAll() {
    return announcementRepository.findAll();
  }

  public Announcement update(Announcement announcement) {
    announcementRepository.findById(announcement.getId()).orElseThrow(() -> new EntityNotFoundException("Announcement with id=" + announcement.getId() + " not found."));
    return announcementRepository.save(announcement);
  }

  public void deleteById(Long id) {
    this.getById(id);
    announcementRepository.deleteById(id);
  }
}
