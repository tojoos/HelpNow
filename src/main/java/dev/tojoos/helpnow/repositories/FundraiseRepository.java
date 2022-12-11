package dev.tojoos.helpnow.repositories;

import dev.tojoos.helpnow.model.Fundraise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundraiseRepository extends JpaRepository<Fundraise, Long> {
}
