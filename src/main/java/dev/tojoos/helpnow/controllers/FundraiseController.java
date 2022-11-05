package dev.tojoos.helpnow.controllers;

import dev.tojoos.helpnow.model.Fundraise;
import dev.tojoos.helpnow.services.FundraiseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Fundraise> addFundraise(@RequestBody Fundraise Fundraise) {
        Fundraise addedFundraise = fundraiseService.add(Fundraise);
        return new ResponseEntity<>(addedFundraise, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Fundraise> updateFundraise(@RequestBody Fundraise Fundraise) {
        Fundraise updatedFundraise = fundraiseService.update(Fundraise);
        return new ResponseEntity<>(updatedFundraise, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteFundraise(@PathVariable Long id) {
        fundraiseService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
