package net.codejava.PasswordResetJWT.util;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
	public class OtpGenerator {
	    public String generateOtp() {
	        return String.valueOf(new Random().nextInt(899999) + 100000); // 6-digit OTP
	    }
	

}
