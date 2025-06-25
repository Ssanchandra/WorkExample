package net.codejava.PasswordResetM.controller;

import net.codejava.PasswordResetM.dto.*;
import net.codejava.PasswordResetM.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    // 1. Send OTP
    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestBody SendOtpRequest request) {
        passwordService.sendOtp(request.getEmail());
        return ResponseEntity.ok("OTP sent to registered email");
    }

    // 2. Verify OTP
    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody VerifyOtpRequest request) {
        boolean isValid = passwordService.verifyOtp(request.getEmail(), request.getOtp());
        return ResponseEntity.ok(isValid ? "OTP is valid" : "Invalid or expired OTP");
    }

    // 3. Change password using verified OTP
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {
        String result = passwordService.changePassword(request.getEmail(), request.getOtp(), request.getNewPassword());
        return ResponseEntity.ok(result);
    }

    // (Optional: Keep the old ones for backward compatibility)
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        passwordService.sendOtp(request.getEmail());
        return ResponseEntity.ok("OTP sent to registered email");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        String result = passwordService.resetPassword(request.getEmail(), request.getOtp(), request.getNewPassword());
        return ResponseEntity.ok(result);
    }
}
