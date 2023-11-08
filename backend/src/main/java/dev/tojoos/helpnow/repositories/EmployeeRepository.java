package dev.tojoos.helpnow.repositories;

import dev.tojoos.helpnow.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
