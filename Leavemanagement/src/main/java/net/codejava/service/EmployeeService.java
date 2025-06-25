package net.codejava.service;

import net.codejava.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Optional<Employee> getEmployeeById(Long id);

    Optional<Employee> updateEmployee(Long id, Employee employeeDetails);

    boolean deleteEmployee(Long id);
}
