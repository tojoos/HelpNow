package dev.tojoos.helpnow.controllers;

import dev.tojoos.helpnow.model.Organization;
import dev.tojoos.helpnow.services.OrganizationService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller exposing organization entities.
 *
 * @author Jan Olszówka
 * @version 1.0
 * @since 2022-11-05
 */
@RestController
@RequestMapping("/organization")
public class OrganizationController {
  private final OrganizationService organizationService;

  public OrganizationController(OrganizationService organizationService) {
    this.organizationService = organizationService;
  }

  @GetMapping("/list")
  public ResponseEntity<List<Organization>> getAllOrganizations() {
    List<Organization> organizations = organizationService.getAll();
    return new ResponseEntity<>(organizations, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Organization> getOrganizationById(@PathVariable Long id) {
    Organization foundOrganization = organizationService.getById(id);
    return new ResponseEntity<>(foundOrganization, HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<Organization> addOrganization(@RequestBody Organization organization) {
    Organization addedOrganization = organizationService.add(organization);
    return new ResponseEntity<>(addedOrganization, HttpStatus.CREATED);
  }

  @PutMapping("/update")
  public ResponseEntity<Organization> updateOrganization(@RequestBody Organization organization) {
    Organization updatedOrganization = organizationService.update(organization);
    return new ResponseEntity<>(updatedOrganization, HttpStatus.OK);
  }

  @DeleteMapping("/{id}/delete")
  public ResponseEntity<?> deleteOrganization(@PathVariable Long id) {
    organizationService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
