package dev.tojoos.helpnow.repositories;

import dev.tojoos.helpnow.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
