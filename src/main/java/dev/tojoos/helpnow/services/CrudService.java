package dev.tojoos.helpnow.services;

import dev.tojoos.helpnow.model.BaseEntity;
import java.util.List;

public interface CrudService<T extends BaseEntity> {
    T add(T baseEntity);

    T getById(Long id);

    List<T> getAll();

    T update(T baseEntity);

    void deleteById(Long id);
}
