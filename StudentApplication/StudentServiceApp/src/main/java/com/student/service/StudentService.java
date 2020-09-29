package com.student.service;

import java.util.List;

import com.student.model.Student;

public interface StudentService {
	public boolean studentLogin(String emailId, String password); 
	public boolean saveStudentDetails(Student student);
	public List<Student> getListofStudentDetails();
	public Student getStudentDetailsById(int studentId);
	public List<Student> getStudentDetailsByName(String name);
	public List<Student> deleteStudentDetailsById(int studentId);
	
	
	
}

	
	
