package com.student.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.student.dao.TokenDao;
import com.student.model.Token;

@Repository("TokenDao")
@EnableTransactionManagement
public class TokenDaoImpl implements TokenDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void saveUserEmail(String email, int studentid) {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			Token token = new Token();
			token.setEmailId(email);
			token.setUserId(studentid);
			session.save(token);

		} catch (Exception e) {
			System.out.println("Exception in saving UserEmail In Token Table :: " + e.getMessage());
		} finally {

			session.flush();
		}
	}

	@Transactional
	public boolean updateToken(String email, String authenticationToken, String secretKey) {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("Update Token set authenticationToken = :authenticationToken ,"
					+ " secretKey = :secretKey where emailId =:emailId");

			query.setParameter("authenticationToken", authenticationToken);
			query.setParameter("emailId", email);
			query.setParameter("secretKey", secretKey);
			int result = query.executeUpdate();

			if (result == 1) {
				return true;
			} else {
				return true;
			}

		} catch (Exception e) {
			System.out.println("Error while updating token :: " + e.getMessage());
			return false;
		} finally {
			session.flush();
		}

	}

	@Transactional
	public int getTokentDetails(String Email) {
		// TODO Auto-generated method stub
		Session session = null;

		try {
			session = sessionFactory.getCurrentSession();
			Query<Token> query = session.createQuery("from Token where emailId =:userEmail");
			query.setParameter("userEmail", Email);
			List<Token> tokensDetails = query.list();
			if (tokensDetails.size() > 0) {
				return tokensDetails.get(0).getTokenId();
			} else {
				return 0;
			}

		} catch (Exception e) {
			System.out.println("Exception while getting token ID :: " + e.getMessage());
		} finally {
			session.flush();
		}
		return 0;
	}

	@Transactional
	public int tokenAuthentication(String token, int emailId) {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(
					"from Token where " + "userId=:userId and " + "authenticationToken=:authenticationToken");
			query.setParameter("userId", emailId);
			query.setParameter("authenticationToken", token);
			List<Token> tokensList = query.list();
			if (tokensList.size() > 0) {
				return tokensList.get(0).getTokenId();
			} else {
				return 0;
			}

		} catch (Exception e) {
			System.out.println("Exception while Authenticating token :: " + e.getMessage());
			return 0;
		} finally {
			session.flush();
		}
	}
	
	@Transactional
	public int tokenAuthentication(String token) {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(
					"from Token where " + "authenticationToken=:authenticationToken");
			query.setParameter("authenticationToken", token);
			List<Token> tokensList = query.list();
			if (tokensList.size() > 0) {
				return tokensList.get(0).getTokenId();
			} else {
				return 0;
			}

		} catch (Exception e) {
			System.out.println("Exception while Authenticating token :: " + e.getMessage());
			return 0;
		} finally {
			session.flush();
		}
	}

}
