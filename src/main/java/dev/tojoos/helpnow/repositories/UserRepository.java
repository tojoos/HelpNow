package dev.tojoos.helpnow.repositories;

import dev.tojoos.helpnow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}