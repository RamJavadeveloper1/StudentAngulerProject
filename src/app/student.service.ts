import { Injectable } from '@angular/core';
import {HttpClient } from '@angular/common/http';
import { Student } from './student';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(private http: HttpClient) { }

  public doRegistration(student: Student){
    return this.http. post('http://localhost:8080/StudentApp/studentRegistrion', student, {responseType: 'text' as 'json'});
  }

  public doLogin(student: Student){
    return this.http. post('http://localhost:8080/StudentApp/login', student, {responseType: 'text' as 'json'});
  }

  public getAllStudent(){
    return this.http.get('http://localhost:8080/StudentApp/students');

  }

  /**
   * getDoctorById
   */
  public getStudentById(StudentId) {
    return this.http.get('http://localhost:8080/StudentApp/findStudentId/' + StudentId);
    }



  /**
   * getDoctorById
   */
  public getStudentByName(name) {
    return this.http.get('http://localhost:8080/StudentApp/findStudentName/' + name);
    }

    /**
     * deletDoctorById
     */
    public deletStudentById(StudentId) {
      return this.http.delete('http://localhost:8080/StudentApp/deleteStudent/' + StudentId);
    }



}
