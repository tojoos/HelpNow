package dev.tojoos.helpnow.controllers;

import dev.tojoos.helpnow.model.Fundraise;
import dev.tojoos.helpnow.services.FundraiseService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller exposing fundraise entities.
 *
 * @author Jan Olszówka
 * @version 1.0
 * @since 2022-11-05
 */
@RestController
@RequestMapping("/fundraise")
public class FundraiseController {
  private final FundraiseService fundraiseService;

  public FundraiseController(FundraiseService fundraiseService) {
    this.fundraiseService = fundraiseService;
  }

  @GetMapping("/list")
  public ResponseEntity<List<Fundraise>> getAllFundraises() {
    List<Fundraise> fundraises = fundraiseService.getAll();
    return new ResponseEntity<>(fundraises, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Fundraise> getFundraiseById(@PathVariable Long id) {
    Fundraise foundFundraise = fundraiseService.getById(id);
    return new ResponseEntity<>(foundFundraise, HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<Fundraise> addFundraise(@RequestBody Fundraise fundraise) {
    Fundraise addedFundraise = fundraiseService.add(fundraise);
    return new ResponseEntity<>(addedFundraise, HttpStatus.CREATED);
  }

  @PutMapping("/update")
  public ResponseEntity<Fundraise> updateFundraise(@RequestBody Fundraise fundraise) {
    Fundraise originalFundraise = fundraiseService.getById(fundraise.getId());
    fundraise.setOrganization(originalFundraise.getOrganization());
    Fundraise updatedFundraise = fundraiseService.update(fundraise);
    return new ResponseEntity<>(updatedFundraise, HttpStatus.OK);
  }

  @DeleteMapping("/{id}/delete")
  public ResponseEntity<?> deleteFundraise(@PathVariable Long id) {
    fundraiseService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
