import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Student } from './student';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class StudentService {
  constructor(private http: HttpClient, private router: Router) {}

  public doRegistration(student: Student) {
    return this.http.post(
      'http://localhost:8080/StudentApp/studentRegistrion',
      student,
      { responseType: 'text' as 'json' }
    );
  }

  public doLogin(student: Student) {
    return this.http.post('http://localhost:8080/StudentApp/login', student, {
      responseType: 'text' as 'json',
    });
  }

  public getAllStudent() {
    // get token from localStorage.
    let token = localStorage.getItem('token');
    // create an instance of Header object.
    // Authorization header.
    const headers = new HttpHeaders({ Authorization: token });
    return this.http.get('http://localhost:8080/StudentApp/students', {
      headers,
    });
  }

  /**
   * getDoctorById
   */
  public getStudentById(StudentId) {
    // get token from localStorage.
    let token = localStorage.getItem('token');
    // create an instance of Header object.
    // Authorization header.
    const headers = new HttpHeaders({ Authorization: token });

    return this.http.get(
      'http://localhost:8080/StudentApp/findStudentId/' + StudentId,
      {
        headers,
      }
    );
  }

  /**
   * getDoctorById
   */
  public getStudentByName(name) {
    // get token from localStorage.
    let token = localStorage.getItem('token');
    // create an instance of Header object.
    // Authorization header.
    const headers = new HttpHeaders({ Authorization: token });

    return this.http.get(
      'http://localhost:8080/StudentApp/findStudentName/' + name,
      { headers }
    );
  }

  /**
   * deletDoctorById
   */
  public deletStudentById(StudentId) {
    // get token from localStorage.
    let token = localStorage.getItem('token');
    // create an instance of Header object.
    // Authorization header.
    const headers = new HttpHeaders({ Authorization: token });

    return this.http.delete(
      'http://localhost:8080/StudentApp/deleteStudent/' + StudentId,
      { headers }
    );
  }

  public isLoggedIn() {
    // create an instance of JwtHelper class
    let jwtHelper = new JwtHelperService();

    //get the token from the localStorage as we have to work on this token
    let token = localStorage.getItem('token');

    // check whether if token have something or it is null.
    if (!token) {
      return false;
    }

    // get the Expiration date of the token by
    // calling getTokenExpirationDate(String) method of
    // JwtHelper class. this method accepts a string
    // value which is nothing

    if (token) {
      let expirationDate = jwtHelper.getTokenExpirationDate(token);
      let isExpired = jwtHelper.isTokenExpired(token);
      return !isExpired;
    }
  }
  /**
   * login
   */
  public loginJwt(student: Student): any {
    return this.http.post(
      'http://localhost:8080/StudentApp/loginJwt',
      student,
      {
        responseType: 'json',
        observe: 'response' as 'body',
      }
    );
  }

  /**
   * logout
   */
  public logout() {
    localStorage.removeItem('token');
    this.router.navigate(['']);
  }
}
