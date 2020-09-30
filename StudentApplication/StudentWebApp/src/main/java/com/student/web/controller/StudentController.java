package com.student.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.student.model.Student;
import com.student.service.StudentService;
import com.student.service.TokenService;
import com.student.web.token.GenerateToken;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "Authorization")
public class StudentController {

	@Autowired(required = true)
	private StudentService studentService;

	@Autowired(required = true)
	private TokenService tokenService;

	GenerateToken generateToken = new GenerateToken();

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
	public List<Student> getStudentList(@RequestHeader("Authorization") String authorizationToken) {

		String token[] = authorizationToken.split(" ");
		int result = tokenService.tokenAuthentication(token[0]);
		if (result > 0) {
			return studentService.getListofStudentDetails();
		} else {
			return null;
		}

	}

	@ResponseBody
	@RequestMapping(value = "/findStudentId/{studentId}", method = RequestMethod.GET)
	public Student getStudentById(@PathVariable final int studentId,
			@RequestHeader("Authorization") String authorizationToken) {

		String token[] = authorizationToken.split(" ");
		int result = tokenService.tokenAuthentication(token[0]);
		if (result > 0) {
			System.out.println("Inside the getStudentByName" + studentId);
			return studentService.getStudentDetailsById(studentId);
		} else {
			return null;
		}

	}

	@ResponseBody
	@RequestMapping(value = "/findStudentName/{studentName}", method = RequestMethod.GET)
	public List<Student> getStudentByName(@PathVariable final String studentName,
			@RequestHeader("Authorization") String authorizationToken) {

		String token[] = authorizationToken.split(" ");
		int result = tokenService.tokenAuthentication(token[0]);
		if (result > 0) {
			System.out.println("Inside the getStudentByName" + studentName);
			return studentService.getStudentDetailsByName(studentName);
		} else {
			return null;
		}

	}

	@ResponseBody
	@RequestMapping(value = "/deleteStudent/{studentId}", method = RequestMethod.DELETE)
	public List<Student> deleteStudentById(@PathVariable final int studentId,
			@RequestHeader("Authorization") String authorizationToken) {
		
		String token[] = authorizationToken.split(" ");
		int result = tokenService.tokenAuthentication(token[0]);
		if (result > 0) {
			System.out.println("Inside the delete" + studentId);
			return studentService.deleteStudentDetailsById(studentId);
		} else {
			return null;
		}

	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String viewIndex() {
		return "index";

	}

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String view() {
		return "errorpage";

	}

	@ResponseBody
	@RequestMapping(value = "/loginJwt", method = RequestMethod.POST)
	public ResponseEntity<Integer> loginStudentJwt(@RequestBody Student student) {
		int status;
		HttpHeaders httpHeaders = null;
		// Authenticate User.
		status = studentService.studentLoginJwt(student.getEmailId(), student.getPassword());

		/*
		 * If token exist then update Token else create and insert the token.
		 */

		if (status > 0) {
			/*
			 * Generate token.
			 */

			String tokenData[] = generateToken.createJWT(student.getEmailId(), "StudentApp", "JWT Token", "admin",
					43200000);
			// get Token.
			String token = tokenData[0];
			System.out.println("Authorization :: " + token);

			// Create the Header Object
			httpHeaders = new HttpHeaders();

			// Add token to the Header.
			httpHeaders.add("Authorization", token);

			// Check if token is already exist.
			long isTokenEmailExits = tokenService.getTokenDetails(student.getEmailId());

			/*
			 * If token exist then update Token else create and insert the token.
			 */

			if (isTokenEmailExits > 0) {
				tokenService.updateToken(student.getEmailId(), token, tokenData[1]);
			} else {
				tokenService.saveUserEmail(student.getEmailId(), status);
				tokenService.updateToken(student.getEmailId(), token, tokenData[1]);
			}
			return new ResponseEntity<Integer>(status, httpHeaders, HttpStatus.OK);
		} else {
			return new ResponseEntity<Integer>(status, httpHeaders, HttpStatus.OK);
		}

	}
}
