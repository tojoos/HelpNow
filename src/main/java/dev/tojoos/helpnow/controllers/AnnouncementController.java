package dev.tojoos.helpnow.controllers;

import dev.tojoos.helpnow.model.Announcement;
import dev.tojoos.helpnow.services.AnnouncementService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller exposing announcement entities.
 *
 * @author Jan Olszówka
 * @version 1.0
 * @since 2022-11-05
 */
@RestController
@RequestMapping("/announcement")
public class AnnouncementController {
  private final AnnouncementService announcementService;

  public AnnouncementController(AnnouncementService announcementService) {
    this.announcementService = announcementService;
  }

  @GetMapping("/list")
  public ResponseEntity<List<Announcement>> getAllAnnouncements() {
    List<Announcement> announcements = announcementService.getAll();
    return new ResponseEntity<>(announcements, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Announcement> getAnnouncementById(@PathVariable Long id) {
    Announcement foundAnnouncement = announcementService.getById(id);
    return new ResponseEntity<>(foundAnnouncement, HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<Announcement> addAnnouncement(@RequestBody Announcement announcement) {
    Announcement addedAnnouncement = announcementService.add(announcement);
    return new ResponseEntity<>(addedAnnouncement, HttpStatus.CREATED);
  }

  @PutMapping("/update")
  public ResponseEntity<Announcement> updateAnnouncement(@RequestBody Announcement announcement) {
    Announcement updatedAnnouncement = announcementService.update(announcement);
    return new ResponseEntity<>(updatedAnnouncement, HttpStatus.OK);
  }

  @DeleteMapping("/{id}/delete")
  public ResponseEntity<?> deleteAnnouncement(@PathVariable Long id) {
    announcementService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
