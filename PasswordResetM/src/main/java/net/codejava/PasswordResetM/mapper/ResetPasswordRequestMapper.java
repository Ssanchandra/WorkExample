package net.codejava.PasswordResetM.mapper;

import net.codejava.PasswordResetM.dto.ResetPasswordRequest;
import net.codejava.PasswordResetM.entity.Employee;

public class ResetPasswordRequestMapper {

    public static Employee mapToEmployee(ResetPasswordRequest dto) {
        Employee employee = new Employee();
        employee.setEmail(dto.getEmail());
        employee.setOtp(dto.getOtp());
        employee.setPassword(dto.getNewPassword()); // This will be encoded in service
        return employee;
    }
}
