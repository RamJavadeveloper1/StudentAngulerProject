package com.student.dao;

public interface TokenDao {

	public void saveUserEmail(String email, int studentid);
	public boolean updateToken(String email, String authenticationToken,String secretKey);
	public int getTokentDetails(String Email);
	public int tokenAuthentication(String tocken, int emailId);
	public int tokenAuthentication(String token);
		
}
