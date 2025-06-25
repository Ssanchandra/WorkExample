package net.codejava.LeaveManagementM.service;

import java.util.List;

import net.codejava.LeaveManagementM.entity.Employee;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    List<Employee> getAllEmployees();
    Employee updateEmployee(Long id, Employee employee);
    void deleteEmployee(Long id);
}
