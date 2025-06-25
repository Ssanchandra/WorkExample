package net.codejava.PasswordResetJWT.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.codejava.PasswordResetJWT.dto.EmployeeResponseDTO;
import net.codejava.PasswordResetJWT.entity.Employee;
import net.codejava.PasswordResetJWT.mapper.EmployeeMapper;
import net.codejava.PasswordResetJWT.repository.EmployeeRepository;
import net.codejava.PasswordResetJWT.util.JwtUtil;
import net.codejava.PasswordResetJWT.util.OtpGenerator;

@Service
public class AuthService {

    @Autowired private EmployeeRepository employeeRepo;
    @Autowired private OtpGenerator otpGenerator;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private JavaMailSender mailSender;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private EmployeeMapper employeeMapper;

    public EmployeeResponseDTO getEmployeeProfile(String email) {
        Employee emp = employeeRepo.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return employeeMapper.toDto(emp);
    }

    public String sendOtp(String email) {
        Employee emp = employeeRepo.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Email not registered"));
        String otp = otpGenerator.generateOtp();
        emp.setOtp(otp);
        emp.setOtpGeneratedTime(LocalDateTime.now());
        employeeRepo.save(emp);

        // Send email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your OTP");
        message.setText("Your OTP is: " + otp);
        mailSender.send(message);

        return "OTP sent to email";
    }

    public String verifyOtp(String email, String otp) {
        Employee emp = employeeRepo.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Email not found"));
        if (!emp.getOtp().equals(otp)) {
            throw new RuntimeException("Invalid OTP");
        }
        if (emp.getOtpGeneratedTime().plusMinutes(10).isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP expired");
        }
        return jwtUtil.generateToken(email);
    }

    public String changePassword(String email, String newPassword, String token) {
        if (!jwtUtil.isTokenValid(token, email)) {
            throw new RuntimeException("Invalid token");
        }
        Employee emp = employeeRepo.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
        emp.setPassword(passwordEncoder.encode(newPassword));
        emp.setOtp(null); // clear OTP
        emp.setOtpGeneratedTime(null);
        employeeRepo.save(emp);
        return "Password changed successfully";
    }
}
