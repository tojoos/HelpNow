package dev.tojoos.helpnow.services;

import dev.tojoos.helpnow.model.BaseEntity;
import java.util.List;

/**
 * Basic crud interface for entities management.
 *
 * @author Jan Olszówka
 * @version 1.0
 * @since 2023-10-31
 */
public interface CrudService<T extends BaseEntity> {
  T add(T baseEntity);

  T getById(Long id);

  List<T> getAll();

  T update(T baseEntity);

  void deleteById(Long id);
}
