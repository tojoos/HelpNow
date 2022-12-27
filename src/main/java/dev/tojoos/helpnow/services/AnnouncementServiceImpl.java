package dev.tojoos.helpnow.services;

import dev.tojoos.helpnow.model.Announcement;
import dev.tojoos.helpnow.repositories.AnnouncementRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    private final AnnouncementRepository announcementRepository;

    public AnnouncementServiceImpl(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    public Announcement add(Announcement announcement) {
        if (announcement.getCreationDateTime() == null) {
            announcement.setCreationDateTime(LocalDateTime.now());
        }
        if (announcement.getStatus() == null) {
            announcement.setStatus("open");
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
