package dev.tojoos.helpnow.repositories;

import dev.tojoos.helpnow.model.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
}
