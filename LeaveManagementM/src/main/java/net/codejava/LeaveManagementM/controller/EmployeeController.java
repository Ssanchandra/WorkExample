package net.codejava.LeaveManagementM.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import net.codejava.LeaveManagementM.dto.EmployeeResponseDTO;
import net.codejava.LeaveManagementM.entity.Department;
import net.codejava.LeaveManagementM.entity.Employee;
import net.codejava.LeaveManagementM.entity.Role;
import net.codejava.LeaveManagementM.mapper.EmployeeMapper;
import net.codejava.LeaveManagementM.repository.DepartmentRepository;
import net.codejava.LeaveManagementM.repository.RoleRepository;
import net.codejava.LeaveManagementM.service.EmployeeService;

@RestController
@RequestMapping("/employees")
@CrossOrigin
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private DepartmentRepository departmentRepo;

    @PostMapping("/create")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        Long roleId = (employee.getRole() != null) ? employee.getRole().getId() : null;
        Long deptId = (employee.getDepartment() != null) ? employee.getDepartment().getId() : null;

        if (roleId == null || deptId == null) {
            return ResponseEntity.badRequest().body("Role ID and Department ID are required.");
        }

        Role role = roleRepo.findById(roleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Role ID"));

        Department department = departmentRepo.findById(deptId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Department ID"));
        System.out.println("Incoming Role ID: " + (employee.getRole() != null ? employee.getRole().getId() : "null"));
        System.out.println("Incoming Department ID: " + (employee.getDepartment() != null ? employee.getDepartment().getId() : "null"));

        employee.setRole(role);
        employee.setDepartment(department);

        Employee saved = employeeService.createEmployee(employee);
        return ResponseEntity.ok(EmployeeMapper.toDTO(saved));
    }





    // ✅ Get Employee by ID (with DTO mapping)
    @GetMapping("/getEmployeeById/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(EmployeeMapper.toDTO(employee));
    }

    // ✅ Get All Employees (mapped to DTOs)
    @GetMapping("/getAll")
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        List<EmployeeResponseDTO> employees = employeeService.getAllEmployees().stream()
                .map(EmployeeMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(employees);
    }

    // ✅ Update Employee
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        employeeService.updateEmployee(id, employee);
        return ResponseEntity.ok("Employee updated successfully!");
    }

    // ✅ Delete Employee
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully!");
    }
}
