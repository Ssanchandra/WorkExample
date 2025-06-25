package net.codejava.LeaveManagementM.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.codejava.LeaveManagementM.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
