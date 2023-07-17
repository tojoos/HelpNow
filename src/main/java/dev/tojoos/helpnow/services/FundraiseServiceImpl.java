package dev.tojoos.helpnow.services;

import dev.tojoos.helpnow.model.Fundraise;
import dev.tojoos.helpnow.repositories.FundraiseRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service implementation used for management of the fundraise entities.
 *
 * @author Jan Olszówka
 * @version 1.0
 * @since 2022-10-31
 */
@Service
public class FundraiseServiceImpl implements FundraiseService {
  private final FundraiseRepository fundraiseRepository;

  public FundraiseServiceImpl(FundraiseRepository fundraiseRepository) {
    this.fundraiseRepository = fundraiseRepository;
  }

  @Override
  public Fundraise add(Fundraise fundraise) {
    return fundraiseRepository.save(fundraise);
  }

  @Override
  public Fundraise getById(Long id) {
    return fundraiseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Fundraise with id=" + id + " not found."));
  }

  @Override
  public List<Fundraise> getAll() {
    return fundraiseRepository.findAll();
  }

  @Override
  public Fundraise update(Fundraise fundraise) {
    fundraiseRepository.findById(fundraise.getId()).orElseThrow(() -> new EntityNotFoundException("Fundraise with id=" + fundraise.getId() + " not found."));
    return fundraiseRepository.save(fundraise);
  }

  @Override
  public void deleteById(Long id) {
    this.getById(id);
    fundraiseRepository.deleteById(id);
  }
}
