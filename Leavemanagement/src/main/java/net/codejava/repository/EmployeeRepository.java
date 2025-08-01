package net.codejava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.codejava.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
