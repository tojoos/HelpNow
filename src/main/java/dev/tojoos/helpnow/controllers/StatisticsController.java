package dev.tojoos.helpnow.controllers;

import dev.tojoos.helpnow.model.Statistics;
import dev.tojoos.helpnow.services.StatisticsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller exposing statistics.
 *
 * @author Jan Olszówka
 * @version 1.0
 * @since 2022-12-11
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {
  private final StatisticsService statisticsService;

  public StatisticsController(StatisticsService statisticsService) {
    this.statisticsService = statisticsService;
  }

  @GetMapping("/getStats")
  public ResponseEntity<Statistics> getStatistics() {
    Statistics statistics = statisticsService.getById(1L);
    return new ResponseEntity<>(statistics, HttpStatus.OK);
  }

  @PutMapping("/update")
  public ResponseEntity<Statistics> updateOrganization(@RequestBody Statistics statistics) {
    Statistics updatedStatistics = statisticsService.update(statistics);
    return new ResponseEntity<>(updatedStatistics, HttpStatus.OK);
  }
}
