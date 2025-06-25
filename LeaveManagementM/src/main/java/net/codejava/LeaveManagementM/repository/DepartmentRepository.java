// DepartmentRepository.java
package net.codejava.LeaveManagementM.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.codejava.LeaveManagementM.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
