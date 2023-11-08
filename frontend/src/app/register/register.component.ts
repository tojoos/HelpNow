import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  form!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      username: '',
      password: '',
      name: '',
      lastName: '',
      imageUrl: '',
      phone: '',
      email: '',
      roles: this.formBuilder.array([{id: '1', name: 'USER'}])
    });
  }

  register() {
    this.http.post('http://localhost:8080/user/add', this.form.getRawValue())
      .subscribe(() => {
        this.router.navigate(['/login']);
      });
  }
  
}