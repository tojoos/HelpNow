package dev.tojoos.helpnow.controllers;

import dev.tojoos.helpnow.model.Employee;
import dev.tojoos.helpnow.services.EmployeeService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller exposing employee entities.
 *
 * @author Jan Olszówka
 * @version 1.0
 * @since 2022-11-05
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
  private final EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping("/list")
  public ResponseEntity<List<Employee>> getAllEmployees() {
    List<Employee> employees = employeeService.getAll();
    return new ResponseEntity<>(employees, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
    Employee foundEmployee = employeeService.getById(id);
    return new ResponseEntity<>(foundEmployee, HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
    Employee addedEmployee = employeeService.add(employee);
    return new ResponseEntity<>(addedEmployee, HttpStatus.CREATED);
  }

  @PutMapping("/update")
  public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
    Employee updatedEmployee = employeeService.update(employee);
    return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
  }

  @DeleteMapping("/{id}/delete")
  public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
    employeeService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
