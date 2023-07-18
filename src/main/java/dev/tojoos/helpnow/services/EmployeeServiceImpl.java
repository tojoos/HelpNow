package dev.tojoos.helpnow.services;

import dev.tojoos.helpnow.model.Employee;
import dev.tojoos.helpnow.repositories.EmployeeRepository;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service implementation used for management of the employee entities.
 *
 * @author Jan Olszówka
 * @version 1.0
 * @since 2023-10-31
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
  private final EmployeeRepository employeeRepository;

  public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  public Employee add(Employee employee) {
    employee.setEmployeeCode(UUID.randomUUID().toString());
    return employeeRepository.save(employee);
  }

  public Employee getById(Long id) {
    return employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee with id=" + id + " not found."));
  }

  public List<Employee> getAll() {
    return employeeRepository.findAll();
  }

  public Employee update(Employee employee) {
    employeeRepository.findById(employee.getId()).orElseThrow(() -> new EntityNotFoundException("Employee with id=" + employee.getId() + " not found."));
    return employeeRepository.save(employee);
  }

  public void deleteById(Long id) {
    this.getById(id);
    employeeRepository.deleteById(id);
  }
}
