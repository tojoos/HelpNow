package dev.tojoos.helpnow.repositories;

import dev.tojoos.helpnow.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(String name);
}
