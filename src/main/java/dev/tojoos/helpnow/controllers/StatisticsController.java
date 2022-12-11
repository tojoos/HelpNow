package dev.tojoos.helpnow.controllers;

import dev.tojoos.helpnow.model.Statistics;
import dev.tojoos.helpnow.services.StatisticsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService StatisticsService) {
        this.statisticsService = StatisticsService;
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
