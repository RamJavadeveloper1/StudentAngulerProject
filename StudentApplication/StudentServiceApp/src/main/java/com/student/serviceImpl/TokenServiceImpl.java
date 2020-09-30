package com.student.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.dao.TokenDao;
import com.student.service.TokenService;

@Service("TokenService")
public class TokenServiceImpl implements TokenService {

	@Autowired
	TokenDao tokenDao;

	public void saveUserEmail(String email, int studentId) {
		tokenDao.saveUserEmail(email, studentId);
	}

	public boolean updateToken(String email, String authenticationToken, String secretKey) {

		return tokenDao.updateToken(email, authenticationToken, secretKey);
	}

	public int getTokenDetails(String email) {

		return tokenDao.getTokentDetails(email);
	}

	public int tokenAuthentication(String token, int emailId) {

		return tokenDao.tokenAuthentication(token, emailId);
	}

	public int tokenAuthentication(String token) {

		return tokenDao.tokenAuthentication(token);
	}

}
