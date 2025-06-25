// DepartmentService.java
package net.codejava.LeaveManagementM.service;

import java.util.List;

import net.codejava.LeaveManagementM.entity.Department;

public interface DepartmentService {
    Department save(Department department);
    List<Department> getAll();
    Department getById(Long id);
    void deleteById(Long id);
}

