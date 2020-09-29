package com.student.dao;

import java.util.List;

import com.student.model.Student;

public interface StudentDao {

	public Student getStudentById(final int studentId);
	public List<Student> getStudentByName(final String name);
	public int studentLogin(String emailId, String password);
	public List<Student> getStudentData();
}
