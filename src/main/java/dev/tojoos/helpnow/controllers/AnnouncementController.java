package dev.tojoos.helpnow.controllers;

import dev.tojoos.helpnow.model.Announcement;
import dev.tojoos.helpnow.services.AnnouncementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController {
    private final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Announcement>> getAllEmployees() {
        List<Announcement> announcements = announcementService.getAll();
        return new ResponseEntity<>(announcements, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Announcement> getEmployeeById(@PathVariable Long id) {
        Announcement foundAnnouncement = announcementService.getById(id);
        return new ResponseEntity<>(foundAnnouncement, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Announcement> addEmployee(@RequestBody Announcement announcement) {
        Announcement addedAnnouncement = announcementService.add(announcement);
        return new ResponseEntity<>(addedAnnouncement, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Announcement> updateEmployee(@RequestBody Announcement announcement) {
        Announcement updatedAnnouncement = announcementService.update(announcement);
        return new ResponseEntity<>(updatedAnnouncement, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        announcementService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
