package net.codejava.PasswordResetM.service;

import net.codejava.PasswordResetM.entity.Employee;
import net.codejava.PasswordResetM.exception.EmailNotFoundException;
import net.codejava.PasswordResetM.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class PasswordService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * ✅ 1. Send OTP to email
     */
    public void sendOtp(String email) {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException("Email not found"));

        // Generate 6-digit OTP
        String otp = String.format("%06d", new Random().nextInt(999999));
        employee.setOtp(otp);
        employee.setOtpGeneratedTime(LocalDateTime.now());
        employeeRepository.save(employee);

        // Send OTP via email
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("OTP for Password Reset");
            message.setText("Your OTP is: " + otp + "\nIt is valid for 5 minutes.");
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to send email. Check SMTP settings.", e);
        }
    }

    /**
     * ✅ 2. Verify only OTP
     */
    public boolean verifyOtp(String email, String otp) {
        Optional<Employee> optional = employeeRepository.findByEmail(email);
        if (optional.isEmpty()) return false;

        Employee employee = optional.get();

        return employee.getOtp() != null &&
               employee.getOtp().equals(otp) &&
               employee.getOtpGeneratedTime() != null &&
               employee.getOtpGeneratedTime().plusMinutes(5).isAfter(LocalDateTime.now());
    }

    /**
     * ✅ 3. Change password after OTP verified
     */
    public String changePassword(String email, String otp, String newPassword) {
        Optional<Employee> optional = employeeRepository.findByEmail(email);
        if (optional.isEmpty()) {
            return "Invalid email";
        }

        Employee employee = optional.get();

        if (employee.getOtp() == null || !employee.getOtp().equals(otp)) {
            return "Invalid OTP";
        }

        if (employee.getOtpGeneratedTime() == null ||
                employee.getOtpGeneratedTime().plusMinutes(5).isBefore(LocalDateTime.now())) {
            return "OTP expired";
        }

        employee.setPassword(passwordEncoder.encode(newPassword));
        employee.setOtp(null);
        employee.setOtpGeneratedTime(null);
        employeeRepository.save(employee);

        return "Password changed successfully";
    }

    /**
     * ✅ 4. Legacy support: Reset password (combines OTP verification + change)
     */
    public String resetPassword(String email, String otp, String newPassword) {
        return changePassword(email, otp, newPassword);
    }
}
