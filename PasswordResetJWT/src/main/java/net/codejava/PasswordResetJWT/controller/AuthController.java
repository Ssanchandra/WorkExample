package net.codejava.PasswordResetJWT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import net.codejava.PasswordResetJWT.dto.EmailRequest;
import net.codejava.PasswordResetJWT.dto.EmployeeResponseDTO;
import net.codejava.PasswordResetJWT.dto.OtpRequest;
import net.codejava.PasswordResetJWT.dto.PasswordChangeRequest;
import net.codejava.PasswordResetJWT.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestBody EmailRequest request) {
        return ResponseEntity.ok(authService.sendOtp(request.getEmail()));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody OtpRequest request) {
        String jwt = authService.verifyOtp(request.getEmail(), request.getOtp());
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody PasswordChangeRequest request) {
        return ResponseEntity.ok(authService.changePassword(
            request.getEmail(), request.getNewPassword(), request.getJwtToken()));
 
   }
    @GetMapping("/profile")
    public ResponseEntity<EmployeeResponseDTO> getProfile(@RequestParam String email) {
        return ResponseEntity.ok(authService.getEmployeeProfile(email));
    }

}
