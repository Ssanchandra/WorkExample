package net.codejava.PasswordResetM.dto;

public class VerifyOtpRequest {
    private String email;
    private String otp;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getOtp() { return otp; }
    public void setOtp(String otp) { this.otp = otp; }
}
