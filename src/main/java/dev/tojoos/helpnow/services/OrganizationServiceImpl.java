package dev.tojoos.helpnow.services;

import dev.tojoos.helpnow.model.Organization;
import dev.tojoos.helpnow.repositories.OrganizationRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service implementation used for management of the organization entities.
 *
 * @author Jan Olszówka
 * @version 1.0
 * @since 2022-10-31
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
  private final OrganizationRepository organizationRepository;

  public OrganizationServiceImpl(OrganizationRepository organizationRepository) {
    this.organizationRepository = organizationRepository;
  }

  @Override
  public Organization add(Organization organization) {
    return organizationRepository.save(organization);
  }

  @Override
  public Organization getById(Long id) {
    return organizationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Organization with id=" + id + " not found."));
  }

  @Override
  public List<Organization> getAll() {
    return organizationRepository.findAll();
  }

  @Override
  public Organization update(Organization organization) {
    organizationRepository.findById(organization.getId()).orElseThrow(
        () -> new EntityNotFoundException("Organization with id=" + organization.getId() + " not found."));
    return organizationRepository.save(organization);
  }

  @Override
  public void deleteById(Long id) {
    this.getById(id);
    organizationRepository.deleteById(id);
  }
}
