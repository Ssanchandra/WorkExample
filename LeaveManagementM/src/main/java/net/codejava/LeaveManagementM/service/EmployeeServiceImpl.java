package net.codejava.LeaveManagementM.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codejava.LeaveManagementM.entity.Department;
import net.codejava.LeaveManagementM.entity.Employee;
import net.codejava.LeaveManagementM.entity.Role;
import net.codejava.LeaveManagementM.repository.DepartmentRepository;
import net.codejava.LeaveManagementM.repository.EmployeeRepository;
import net.codejava.LeaveManagementM.repository.RoleRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    // ✅ Create employee with valid Role and Department
    @Override
    public Employee createEmployee(Employee employee) {
        Long roleId = employee.getRole().getId();
        Long deptId = employee.getDepartment().getId();

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Invalid or missing Role ID"));

        Department department = departmentRepository.findById(deptId)
                .orElseThrow(() -> new RuntimeException("Invalid or missing Department ID"));

        employee.setRole(role);
        employee.setDepartment(department);

        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // ✅ Update employee with validated Role and Department
    @Override
    public Employee updateEmployee(Long id, Employee updated) {
        Employee existing = getEmployeeById(id);

        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setPhone(updated.getPhone());
        existing.setPhotoUrl(updated.getPhotoUrl());
        existing.setDob(updated.getDob());
        existing.setAddress(updated.getAddress());
        existing.setJoinDate(updated.getJoinDate());
        existing.setStatus(updated.getStatus());

        // Fetch and set Role
        if (updated.getRole() != null && updated.getRole().getId() != null) {
            Role role = roleRepository.findById(updated.getRole().getId())
                    .orElseThrow(() -> new RuntimeException("Invalid or missing Role ID"));
            existing.setRole(role);
        }

        // Fetch and set Department
        if (updated.getDepartment() != null && updated.getDepartment().getId() != null) {
            Department dept = departmentRepository.findById(updated.getDepartment().getId())
                    .orElseThrow(() -> new RuntimeException("Invalid or missing Department ID"));
            existing.setDepartment(dept);
        }

        return employeeRepository.save(existing);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
