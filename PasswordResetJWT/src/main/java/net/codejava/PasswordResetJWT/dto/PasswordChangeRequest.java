package net.codejava.PasswordResetJWT.dto;


	public class PasswordChangeRequest {
	    private String email;
	    private String newPassword;
	    private String jwtToken;
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getNewPassword() {
			return newPassword;
		}
		public void setNewPassword(String newPassword) {
			this.newPassword = newPassword;
		}
		public String getJwtToken() {
			return jwtToken;
		}
		public void setJwtToken(String jwtToken) {
			this.jwtToken = jwtToken;
		}
	

}
