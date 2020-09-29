package com.student.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.student.dao.StudentDao;
import com.student.model.Student;

@Repository("StudentDao")
@EnableTransactionManagement
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentDaoImpl.class);

	@Transactional
	public Student getStudentById(int studentId) {
		// TODO Auto-generated method stub

		Student student = null;
		Session session = sessionFactory.getCurrentSession();
		String query = "from Student where student_id=:studentId";
		try {
			student = (Student) session.createQuery(query).setParameter("studentId", studentId).uniqueResult();

		} catch (Exception e) {
			LOGGER.error("Exception while getting partner details :: ", e);
		}
		return student;
	}

	@Transactional
	public int studentLogin(String emailId, String password) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		try {

			Query<Student> query = session.createQuery("from Student where emailId=:emailId and password=:password");
			query.setParameter("emailId", emailId);
			query.setParameter("password", password);
			List<Student> list = query.list();

			int size = list.size();
			if (size == 1) {
				return list.get(0).getStudentID();
			} else {
				return -1;
			}

		} catch (Exception e) {
			System.out.println("Excecption while saving student Details : " + e.getMessage());
			return 0;
		}

		finally {
			session.flush();
		}
	}

	@Transactional
	public List<Student> getStudentData() {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();

			Query<Student> query = session.createQuery("from Student");
			List<Student> list = query.list();

			if (list.size() > 0) {
				return list;
			} else {
				return null;
			}

		} catch (Exception exception) {
			System.out.println("Excecption while saving student Details : " + exception.getMessage());
			return null;
		} finally {
			session.flush();
		}

	}

	@Transactional
	public List<Student> getStudentByName(String name) {
		// TODO Auto-generated method stub
		List<Student> student=null;
		Session session = sessionFactory.getCurrentSession();
		String query = "from Student where name=:studentName";
		try {
			 student = session.createQuery(query).setParameter("studentName", name).list();

		} catch (Exception e) {
			LOGGER.error("Exception while getting partner details :: ", e);
		}
		return student;
	}

}
