package com.student.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.dao.BaseDao;
import com.student.dao.StudentDao;
import com.student.model.Student;
import com.student.service.StudentService;

@Service("StudentService")
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentDao studentDao;
	@Autowired
	BaseDao baseDao;

	public boolean saveStudentDetails(Student student) {
		// TODO Auto-generated method stub
		boolean status = false;
		if (student != null) {
			try {
				status = baseDao.save(student);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return status;
	}

	public List<Student> deleteStudentDetailsById(int studentId) {
		// TODO Auto-generated method stub
		try {
			Student student=studentDao.getStudentById(studentId);
			if(student!=null) {
				baseDao.delete(student);
			}
			
			return studentDao.getStudentData();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	public Student getStudentDetailsById(int studentId) {
		// TODO Auto-generated method stub
		return studentDao.getStudentById(studentId);
	}

	public List<Student> getStudentDetailsByName(String name) {
		// TODO Auto-generated method stub
		return studentDao.getStudentByName(name);
	}
	
	public List<Student> getListofStudentDetails() {
		// TODO Auto-generated method stub
		return studentDao.getStudentData();
	}

	public boolean studentLogin(String emailId, String password) {
		int result=studentDao.studentLogin(emailId, password);
		boolean status=false;
		if(result!=-1) {
			status=true;
		}
		return status;
	}

}
