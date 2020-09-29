package com.student.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.student.dao.BaseDao;

@Repository("BaseDao")
@EnableTransactionManagement
public class BaseDaoImpl implements BaseDao {

	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseDaoImpl.class);

	@Transactional
	public boolean save(Object obj) throws Exception {
		// TODO Auto-generated method stub
		LOGGER.info("Inside BaseDAO Save");
		boolean isSuccess = false;
		try {
			this.sessionFactory.getCurrentSession().save(obj);
			isSuccess = true;

		} catch (ConstraintViolationException e) {
			throw new Exception("Duplicate records found" + e);
		} catch (HibernateException e) {
			throw new Exception(e);
		} catch (Exception e) {
			LOGGER.error("" + e);
		}
		return isSuccess;
	}

	@Transactional
	public boolean update(Object obj) throws Exception {
		
		LOGGER.info("Inside BaseDAO Update");
		boolean isSuccess = false;
		try {
			this.sessionFactory.getCurrentSession().update(obj);
			isSuccess = true;
		} catch (HibernateException e) {
			throw new HibernateException(e);
		} catch (Exception e) {
			LOGGER.error(""+e);
		}
		return isSuccess;
	}
	
	@Transactional
	public boolean saveOrUpdate(Object obj) throws Exception {
		LOGGER.info("Inside BaseDAO saveOrUpdate");
		boolean isSuccess = false;
		try {
			this.sessionFactory.getCurrentSession().saveOrUpdate(obj);
			isSuccess = true;
		} catch (ConstraintViolationException e) {
			throw new Exception(e);
		} catch (HibernateException e) {

			throw new Exception(e);
		} catch (Exception e) {
			LOGGER.error(""+e);
		}
		return isSuccess;
	}
	
	@Transactional
	public boolean delete(Object obj) throws Exception {
		LOGGER.info("Inside BaseDAO delete");
		boolean isSuccess = false;
		try {
			this.sessionFactory.getCurrentSession().delete(obj);
			isSuccess = true;
		} catch (HibernateException e) {
			throw new Exception(e);
		} catch (Exception e) {
			LOGGER.error(""+e);
		}
		return isSuccess;
	}

}
