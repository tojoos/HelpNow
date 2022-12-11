package dev.tojoos.helpnow.services;

import dev.tojoos.helpnow.model.Fundraise;
import dev.tojoos.helpnow.model.Statistics;
import dev.tojoos.helpnow.repositories.StatisticsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsRepository statisticsRepository;
    private final FundraiseService fundraiseService;

    public StatisticsServiceImpl(StatisticsRepository statisticsRepository, FundraiseService fundraiseService) {
        this.statisticsRepository = statisticsRepository;
        this.fundraiseService = fundraiseService;
    }

    @Override
    public Statistics add(Statistics statistics) {
        return statisticsRepository.save(statistics);
    }

    @Override
    public Statistics getById(Long id) {
        return statisticsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Statistics with id=" + id + " not found."));
    }

    @Override
    public List<Statistics> getAll() {
        return statisticsRepository.findAll();
    }

    @Override
    public Statistics update(Statistics statistics) {
        statisticsRepository.findById(statistics.getId()).orElseThrow(() -> new EntityNotFoundException("Statistics with id=" + statistics.getId() + " not found."));
        return statisticsRepository.save(statistics);
    }

    @Override
    public void deleteById(Long id) {
        this.getById(id);
        statisticsRepository.deleteById(id);
    }

    public void recalculateStatistics() {
        List<Fundraise> fundraises = fundraiseService.getAll();

        Long totalFundsRaised = fundraises.stream()
                .mapToLong(Fundraise::getRaisedAmount)
                .sum();

        Long totalCompletedFundraises = fundraises.stream()
                .filter(fundraise -> fundraise.getEndingDate().isBefore(LocalDate.now()))
                .count();

        if (statisticsRepository.findAll().size() == 0) {
            log.debug("No statistics found in database, recalculating from scratch.");
            Statistics statistics = new Statistics();

            statistics.setTotalFundsRaised(totalFundsRaised);
            statistics.setCompletedFundraises(totalCompletedFundraises);
            statistics.setPeopleHelped(0L);
            statistics.setServiceVisits(0L);

            this.add(statistics);
        } else {
            log.debug("Statistics found in database, updating values.");
            Statistics statistics = this.getById(1L);

            statistics.setTotalFundsRaised(totalFundsRaised);
            statistics.setCompletedFundraises(totalCompletedFundraises);

            this.update(statistics);
        }
    }

}
