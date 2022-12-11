package dev.tojoos.helpnow.services;


import dev.tojoos.helpnow.model.Statistics;

public interface StatisticsService extends CrudService<Statistics> {
    void recalculateStatistics();
}
