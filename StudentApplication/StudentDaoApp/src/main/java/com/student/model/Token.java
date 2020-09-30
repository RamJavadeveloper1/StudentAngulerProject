package com.student.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Token")
public class Token {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="token_id")
	private int tokenId;
	@Column(name="user_id",unique = true)
	private int userId;
	@Column(name="authenticationToken")
	private String authenticationToken;
	@Column(name="secretKey")
	private String secretKey;
	@Column(name = "email_id")
	private String emailId;
	
	public Token() {
		
	}
	
	public Token(int tokenId, int userId, String authenticationToken, String secretKey, String emailId) {
		super();
		this.tokenId = tokenId;
		this.userId = userId;
		this.authenticationToken = authenticationToken;
		this.secretKey = secretKey;
		this.emailId = emailId;
	}

	public int getTokenId() {
		return tokenId;
	}

	public void setTokenId(int tokenId) {
		this.tokenId = tokenId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAuthenticationToken() {
		return authenticationToken;
	}

	public void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "Token [tokenId=" + tokenId + ", userId=" + userId + ", authenticationToken=" + authenticationToken
				+ ", secretKey=" + secretKey + ", emailId=" + emailId + "]";
	}
	
	
	
	
	
	
	

}
