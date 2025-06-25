package net.codejava.PasswordResetJWT.mapper;

import net.codejava.PasswordResetJWT.dto.EmployeeResponseDTO;
import net.codejava.PasswordResetJWT.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeResponseDTO toDto(Employee employee) {
        if (employee == null) return null;
        return new EmployeeResponseDTO(
            employee.getId(),
            employee.getName(),
            employee.getEmail()
        );
    }

    // Optional: if you want to convert DTO back to Entity
    public Employee toEntity(EmployeeResponseDTO dto) {
        if (dto == null) return null;

        Employee emp = new Employee();
        emp.setId(dto.getId());
        emp.setName(dto.getName());
        emp.setEmail(dto.getEmail());
        return emp;
    }
}
