import { Component, OnInit } from '@angular/core';
import { Student } from '../student';
import { StudentService } from '../student.service';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpHeaders } from '@angular/common/http';
import { AppRoutingModule } from '../app-routing.module';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  student: Student = new Student();

  constructor(private service: StudentService, private router: Router) {}

  form = new FormGroup({
    email: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
  });

  login(): any {
    this.service.doLogin(this.student).subscribe((response) => {
      this.router.navigate(['/students']);
    });
  }

  loginJwt(): any {
    this.service.loginJwt(this.student).subscribe((response) => {
      console.log(response);
      console.log(response.headers.get('Authorization'));
      if (response.body > 0) {
        let token = response.headers.get('Authorization');
        localStorage.setItem('token', token);
        localStorage.setItem('id', response.body);
        this.router.navigate(['/students']);
      }

      if (response.body === -1) {
        alert(
          'please register before login Or Invalid combination of Email and password'
        );
      }
    });
  }

  ngOnInit(): void {
    if (this.service.isLoggedIn()) {
      this.router.navigate(['/students', localStorage.getItem('id')]);
    } else {
      this.router.navigate(['/login']);
    }
  }

  get Email() {
    return this.form.get('email');
  }

  get Password() {
    return this.form.get('password');
  }
}
