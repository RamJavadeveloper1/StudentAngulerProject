package com.student.service;

public interface TokenService {

	public void saveUserEmail(String email, int studentId);

	public boolean updateToken(String email, String authenticationToken, String secretKey);

	public int getTokenDetails(String email);

	public int tokenAuthentication(String token, int emailId);
	public int tokenAuthentication(String token);
}
