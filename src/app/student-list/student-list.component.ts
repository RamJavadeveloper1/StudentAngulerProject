import { Component, OnInit } from '@angular/core';
import { StudentService } from '../student.service';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css'],
})
export class StudentListComponent implements OnInit {
  students: any;
  id: number;
  name: string;

  constructor(private service: StudentService) {}

  ngOnInit(): void {
    let resp = this.service.getAllStudent();
    resp.subscribe((data) => (this.students = data));
  }

  /**
   * findStudentById()
   */
  public findStudentById() {
    let resp = this.service.getStudentById(this.id);
    return resp.subscribe((data) => (this.students = data));
  }

  /**
   * findStudentByName()
   */
  public findStudentByName() {
    let resp = this.service.getStudentByName(this.name);
    return resp.subscribe((data) => (this.students = data));
  }

  /**
   * deletedoctorById()
   */
  public deleteStudentById(id: number) {
    let resp = this.service.deletStudentById(id);
    return resp.subscribe((data) => (this.students = data));
  }
}
