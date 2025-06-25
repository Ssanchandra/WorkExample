package net.codejava.PasswordResetM.mapper;

import net.codejava.PasswordResetM.dto.ForgotPasswordRequest;
import net.codejava.PasswordResetM.entity.Employee;

public class ForgotPasswordRequestMapper {

    public static Employee mapToEmployee(ForgotPasswordRequest dto) {
        Employee employee = new Employee();
        employee.setEmail(dto.getEmail());
        return employee;
    }
}
