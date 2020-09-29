package com.student.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.student.model.Student;
import com.student.service.StudentService;

@Controller
@CrossOrigin("*")
public class StudentController {
	@Autowired(required = true)
	StudentService studentService;

	@ResponseBody
	@RequestMapping(value = "/studentRegistrion", method = RequestMethod.POST)
	public boolean saveStudent(@RequestBody Student student) {
		boolean status = studentService.saveStudentDetails(student);
		return status;
	}

	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public boolean loginStudent(@RequestBody Student student) {
		boolean status = studentService.studentLogin(student.getEmailId(), student.getPassword());
		return status;
	}

	@ResponseBody
	@RequestMapping(value = "/students", method = RequestMethod.GET)
	public List<Student> getStudentList() {
		return studentService.getListofStudentDetails();
	}

	@ResponseBody
	@RequestMapping(value = "/findStudentId/{studentId}", method = RequestMethod.GET)
	public Student getStudentById(@PathVariable final int studentId) {
		System.out.println("Inside the getStudentByName"+studentId);
		return studentService.getStudentDetailsById(studentId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/findStudentName/{studentName}", method = RequestMethod.GET)
	public List<Student> getStudentByName(@PathVariable final String studentName) {
		System.out.println("Inside the getStudentByName"+studentName);
		return studentService.getStudentDetailsByName(studentName);
	}

	@ResponseBody
	@RequestMapping(value = "/deleteStudent/{studentId}", method = RequestMethod.DELETE)
	public List<Student> deleteStudentById(@PathVariable final int studentId) {
		System.out.println("Inside the delete"+studentId);
		return studentService.deleteStudentDetailsById(studentId);
	}
	

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String viewIndex() {
		return "index";

	}
	
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String view() {
		return "errorpage";

	}
}
