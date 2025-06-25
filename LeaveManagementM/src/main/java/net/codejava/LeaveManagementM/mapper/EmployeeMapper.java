package net.codejava.LeaveManagementM.mapper;

import net.codejava.LeaveManagementM.dto.EmployeeResponseDTO;
import net.codejava.LeaveManagementM.entity.Employee;

public class EmployeeMapper {

    public static EmployeeResponseDTO toDTO(Employee employee) {
        EmployeeResponseDTO dto = new EmployeeResponseDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setPhone(employee.getPhone());
        dto.setPhotoUrl(employee.getPhotoUrl());
        dto.setDob(employee.getDob() != null ? employee.getDob().toString() : null);
        dto.setAddress(employee.getAddress());
        dto.setJoinDate(employee.getJoinDate() != null ? employee.getJoinDate().toString() : null);
        dto.setStatus(employee.getStatus());

        if (employee.getRole() != null) {
            dto.setRoleId(employee.getRole().getId());
            dto.setRoleName(employee.getRole().getName());
        }

        if (employee.getDepartment() != null) {
            dto.setDepartmentId(employee.getDepartment().getId());
            dto.setDepartmentName(employee.getDepartment().getName());
        }

        return dto;
    }
}
